package maze.logic;

import java.awt.Point;
import java.util.Random;

import maze.logic.Game.*;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;
	private GameState gameState = GameState.RUNNING;
	private GameMode gameMode;
	private char[][] maze;

	public Maze(char[][] maze, GameMode gameMode) {
		this.gameMode = gameMode;
		this.maze = maze.clone(); 
		readMaze();

		setChar(hero.getPosition(), hero.getChar());
		setChar(dragon.getPosition(), dragon.getChar());
		setChar(sword.getPosition(), sword.getChar());
		setChar(exit.getPosition(), exit.getChar());
	}

	private void readMaze(){
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++){
				switch (getChar(new Point (i,j))){
				case 'H':
					hero = new Hero(new Point(i, j));
					break;
				case 'D':
					dragon = new Dragon(new Point(i,j));
					break;
				case 'E':
					sword = new Sword(new Point(i, j));
					break;
				case 'S':
					exit = new Exit(new Point(i, j));
					break;
				}
			}
		}
	}

	private void setChar(Point position, char c) {
		maze[position.y][position.x] = c;
	}

	private void unsetChar(Point position) {
		maze[position.y][position.x] = ' ';
	}

	private char getChar(Point position) {
		return maze[position.y][position.x];
	}

	public boolean moveHero(Direction direction) {
		boolean ret = false;
		
		if (canMove(hero.getPosition(), direction)) {
			unsetChar(hero.getPosition());
			hero.move(direction);
			if (!sword.getPickedUp() && sword.getPosition().equals(hero.getPosition())) {
				pickUpSword();
			}
			setChar(hero.getPosition(), hero.getChar());
			ret = true;
		}
		
		update();
		return ret;
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

	public void updateDragon(){
		if(!dragon.isAlive())
			return;

		unsetChar(dragon.getPosition());

		switch (gameMode){
		case STATIONARY:
			break;
		case RANDOM_MOVEMENT:
			moveDragon(getValidDragonRandomDirection());
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
					moveDragon(getValidDragonRandomDirection());
				default:
					break;
				}
			}
			break;
		}

		setChar(dragon.getPosition(), dragon.getChar());
	}

	public void moveDragon(Direction direction) {
		if(!dragon.isAlive())
			return;

		dragon.move(direction);
		update();
	}

	private boolean canMove(Point position, Direction direction) {
		switch (direction) {
		case UP:
			if (getChar(new Point(position.x, position.y -1)) == 'X' 
			|| (getChar(new Point(position.x, position.y -1)) == 'S' && dragon.isAlive()))
				return false;
			break;
		case DOWN:
			if (getChar(new Point(position.x, position.y + 1)) == 'X'
			|| (getChar(new Point(position.x, position.y + 1)) == 'S' && dragon.isAlive()))
				return false;
			break;
		case RIGHT:
			if (getChar(new Point(position.x + 1, position.y)) == 'X' 
			|| (getChar(new Point(position.x + 1, position.y)) == 'S' && dragon.isAlive()))
				return false;
			break;
		case LEFT:
			if (getChar(new Point(position.x - 1, position.y)) == 'X'
			|| (getChar(new Point(position.x - 1, position.y)) == 'S' && dragon.isAlive()))
				return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}

	public void update() {
		//Checks if the hero is next to a dragon
		if (isHeroNextToDragon()) {
			//If the hero has a sword equipped, then kill the dragon adjacent to him
			if(hero.getSwordEquipped()){
				dragon.kill();
				unsetChar(dragon.getPosition());
				setChar(hero.getPosition(), hero.getChar());
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
			if (dragon.getPosition().equals(sword.getPosition()))
				setChar(sword.getPosition(), 'F');
			else
				setChar(sword.getPosition(), 'E');
		}
	}

	private void pickUpSword() {
		hero.equipSword();
		sword.pickUp();
		unsetChar(sword.getPosition());
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
		if (hero.getPosition().equals(exit.getPosition())) {
			return true;
		}
		return false;
	}

	public GameState getGameState() {
		return gameState;
	}

	private Direction getValidDragonRandomDirection(){
		Direction direction;
		do{
			direction = getRandomDirection();
		} while (!canMove(dragon.getPosition(), direction));
		return direction;
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

	public Hero getHero() {
		return hero;
	}
	
	public Sword getSword() {
		return sword;
	}
	
	public Dragon getDragon() {
		return dragon;
	}
	
}
