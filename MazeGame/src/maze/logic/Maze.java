package maze.logic;

import java.util.Random;

import javax.naming.SizeLimitExceededException;

import maze.cli.CommandLineInterface;
import maze.logic.Game.*;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;
	private CommandLineInterface cli = new CommandLineInterface();
	private GameState gameState = GameState.RUNNING;
	private GameMode gameMode;
	private char[][] maze;

	public Maze(char[][] maze) {
		this.maze = maze.clone(); 
				
		hero = new Hero(1, 1);
		setChar(hero.getX(), hero.getY(), hero.getChar());

		dragon = new Dragon(1, 3);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());

		sword = new Sword(1, 8);
		setChar(sword.getX(), sword.getY(), sword.getChar());

		exit = new Exit(9, 5);
		setChar(exit.getX(), exit.getY(), exit.getChar());

		cli.print("What mode would you like to play in?");
		cli.print("S for Stationary Dragon.");
		cli.print("R for Random Movement");
		cli.print("Everything for Sleeping and Random Movement");
		gameMode = cli.getGameMode();
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

	private void moveHero(Direction direction) {
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

	public Direction getRandomDirection(){
		Random random = new Random();
		int direction = random.nextInt(5);

		switch (direction){
		case 0:
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.RIGHT;
		case 3:
			return Direction.LEFT;
		default:
			return Direction.STAY;
		}
	}

	public void nextTurn(){
		moveHero(cli.getHeroDirection());
		updateDragon();
		update();
	}

	private void updateDragon(){
		unsetChar(dragon.getX(), dragon.getY());

		switch (gameMode){
		case STATIONARY:
			break;
		case RANDOM_MOVEMENT:
			moveDragon();
			break;
		case SLEEP_RANDOM_MOVEMENT:
			Random random = new Random();
			
			if(dragon.isSleeping()){
				switch(random.nextInt(2)){
				case 0:
					dragon.setSleeping(false);
					break;
				default:
					break;
				}
			} else {
				switch(random.nextInt(3)){
				case 0:
					dragon.setSleeping(true);
					break;
				case 1:
					moveDragon();
				default:
					break;
				}
			}
			break;
		}

		setChar(dragon.getX(), dragon.getY(), dragon.getChar());
	}

	private void moveDragon() {
		if(!dragon.isAlive())
			return;

		Direction direction; 

		do{
			direction = getRandomDirection();
		} while (!canMove(dragon.getX(), dragon.getY(), direction));

		dragon.move(direction);
	}

	private boolean canMove(int x, int y, Direction direction) {
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
			} //Otherwise, if the dragon is awake, it wins
			else{
				if(!dragon.isSleeping())
					gameState = GameState.DRAGON_WIN;
			}
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
