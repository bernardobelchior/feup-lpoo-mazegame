package maze.logic;

import java.util.Random;
import maze.cli.CommandLineInterface;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;
	private CommandLineInterface cli;
	private GameState gameState;

	private char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	public Maze() {
		cli = new CommandLineInterface();
		gameState = GameState.RUNNING;

		hero = new Hero(1, 1);
		setChar(hero.getX(), hero.getY(), hero.getChar());

		dragon = new Dragon(1, 3);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());

		sword = new Sword(1, 8);
		setChar(sword.getX(), sword.getY(), sword.getChar());

		exit = new Exit(9, 5);
		setChar(exit.getX(), exit.getY(), exit.getChar());

	}

	private void setChar(int x, int y, char c) {
		maze[y][x] = c;
	}

	private void unsetChar(int x, int y) {
		maze[y][x] = ' ';
	}

	private char getChar(int x, int y) {
		return maze[y][x];
	}

	private void moveHero(Game.Direction direction) {
		if (canMove(hero.getX(), hero.getY(), direction)) {
			unsetChar(hero.getX(), hero.getY());
			hero.move(direction);
			if (!sword.getPickedUp() && sword.getX() == hero.getX() && sword.getY() == hero.getY()) {
				pickUpSword();
			}
			setChar(hero.getX(), hero.getY(), hero.getChar());
		} else
			cli.print("You cannot move in this direction");
	}

	public Game.Direction getRandomDirection(){
		Random random = new Random();
		int direction = random.nextInt(5);

		switch (direction){
		case 0:
			return Game.Direction.UP;
		case 1:
			return Game.Direction.DOWN;
		case 2:
			return Game.Direction.RIGHT;
		case 3:
			return Game.Direction.LEFT;
		default:
			return Game.Direction.STAY;
		}
	}

	public void nextTurn(){
		moveHero(cli.getHeroDirection());
		moveDragon();
		update();
	}

	private void moveDragon() {
		if(!dragon.isAlive())
			return;

		Game.Direction direction; 

		do{
			direction = getRandomDirection();
		} while (!canMove(dragon.getX(), dragon.getY(), direction));

		unsetChar(dragon.getX(), dragon.getY());
		dragon.move(direction);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());

	}

	private boolean canMove(int x, int y, Game.Direction direction) {
		switch (direction) {
		case UP:
			if (getChar(x, y - 1) == 'X' || (getChar(x, y - 1) == 'S' && dragon.isAlive()))
				return false;
			break;
		case DOWN:
			if (getChar(x, y + 1) == 'X' || (getChar(x, y + 1) == 'S' && dragon.isAlive()))
				return false;
			break;
		case RIGHT:
			if (getChar(x + 1, y) == 'X' || (getChar(x + 1, y) == 'S' && dragon.isAlive()))
				return false;
			break;
		case LEFT:
			if (getChar(x - 1, y) == 'X' || (getChar(x - 1, y) == 'S' && dragon.isAlive()))
				return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}

	private void update() {
		//Checks if the hero is next to a dragon
		if (isHeroNextToDragon()) {
			//If the hero has a sword equipped, then kill the dragon adjacent to him
			if(hero.getSwordEquipped()){
				dragon.kill();
				unsetChar(dragon.getX(), dragon.getY());
			} //Otherwise the dragon wins
			else 
				gameState = GameState.DRAGON_WIN;
		}

		//Update game state
		if (isHeroOnExit() && !dragon.isAlive()) {
			gameState = GameState.HERO_WIN;
		}

		//Update dragon and sword characters on maze
		if(!sword.getPickedUp()){
			if (dragon.getX()==sword.getX() && dragon.getY()==sword.getY())
				setChar(sword.getX(), sword.getY(), 'F');
			else
				setChar(sword.getX(), sword.getY(), 'E');
		}
	}

	private void pickUpSword() {
		hero.equipSword();
		sword.pickUp();
		unsetChar(sword.getX(), sword.getY());
	}

	private boolean isHeroNextToDragon() {
		if(dragon.isAlive()){
			// Checks if the hero is in an adjacent square to the dragon or in the same square
			if ((Math.abs(hero.getX() - dragon.getX()) <= 1 && Math.abs(hero.getY() - dragon.getY()) == 0)
					|| (Math.abs(hero.getX() - dragon.getX()) == 0 && Math.abs(hero.getY() - dragon.getY()) == 1)) {
				return true;
			}
		}
		return false;
	}

	private boolean isHeroOnExit() {
		if (hero.getX() == exit.getX() && hero.getY() == exit.getY()) {
			return true;
		}
		return false;
	}

	public GameState getGameState() {
		return gameState;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				result += " " + maze[i][j];
			}
			result += '\n';
		}

		return result;
	}

}
