package maze.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.Game.*;

public class Maze {
	private Hero hero;
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
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
		for(int i=0;i<dragons.size();i++){
			setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
		}
		setChar(sword.getPosition(), sword.getChar());
		setChar(exit.getPosition(), exit.getChar());
	}

	private void readMaze(){
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++){
				switch (getChar(new Point (i,j))){
				case 'H':
					hero = new Hero(new Point(i, j));
					setChar(new Point(i, j), ' ');
					break;
				case 'D':
					dragons.add(new Dragon(new Point(i, j)));
					setChar(new Point(i, j), ' ');
					break;
				case 'E':
					sword = new Sword(new Point(i, j));
					setChar(new Point(i, j), ' ');
					break;
				case 'S':
					exit = new Exit(new Point(i, j));
					setChar(new Point(i, j), ' ');
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
		if (canMove(hero.getPosition(), direction)) {
			unsetChar(hero.getPosition());
			hero.move(direction);
			update();
			setChar(hero.getPosition(), hero.getChar());
			return true;
		}

		return false;
	}


	public void updateDragons(){
		switch (gameMode){
		case STATIONARY:
			for(int i = 0; i < dragons.size(); i++){
				setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
			}
			break;
		case RANDOM_MOVEMENT:
			for(int i = 0; i < dragons.size(); i++){
				unsetChar(dragons.get(i).getPosition());
				moveDragon(i, getValidDragonRandomDirection(dragons.get(i).getPosition()));
				setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
			}
			break;
		case SLEEP_RANDOM_MOVEMENT:
			Random random = new Random();
			for(int i = 0; i < dragons.size(); i++){
				if(dragons.get(i).isSleeping()){
					switch(random.nextInt(2)){
					case 0:
						dragons.get(i).setSleeping(false);
						break;
					default:
						break;
					}
				} else {
					switch(random.nextInt(3)){
					case 0:
						dragons.get(i).setSleeping(true);
						break;
					case 1:
						moveDragon(i, getValidDragonRandomDirection(dragons.get(i).getPosition()));
					default:
						break;
					}
				}
			}
			break;
		}
	}

	public void moveDragon(int i, Direction direction) {
		if(i > dragons.size())
			return; 

		dragons.get(i).move(direction);
		update();
	}

	private boolean canMove(Point position, Direction direction) {
		switch (direction) {
		case UP:
			if (getChar(new Point(position.x, position.y -1)) == 'X' 
			|| (getChar(new Point(position.x, position.y -1)) == 'S' && dragons.size() > 0))
				return false;
			break;
		case DOWN:
			if (getChar(new Point(position.x, position.y +1)) == 'X' 
			|| (getChar(new Point(position.x, position.y +1)) == 'S' && dragons.size() > 0))
				return false;
			break;
		case RIGHT:
			if (getChar(new Point(position.x+1, position.y)) == 'X' 
			|| (getChar(new Point(position.x+1, position.y)) == 'S' && dragons.size() > 0))
				return false;
			break;
		case LEFT:
			if (getChar(new Point(position.x-1, position.y)) == 'X' 
			|| (getChar(new Point(position.x-1, position.y)) == 'S' && dragons.size() > 0))
				return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}

	public void update() {
		//Checks if the hero is able to pick up the sword and picks it up
		if (!sword.getPickedUp() && sword.getPosition().equals(hero.getPosition())) {
			pickUpSword();
		}
		
		//Returns an ArrayList of indexes of dragons adjacent to the hero
		ArrayList<Integer> adjacentDragons = getAdjacentDragons(hero.getPosition());
		
		if (adjacentDragons.size() > 0) {
			//If the hero has a sword equipped, then kill the dragon adjacent to him
			for(int i = adjacentDragons.size() -1 ; i >= 0; i--){
				if(hero.getSwordEquipped()) {
					dragons.remove(adjacentDragons.get(i).intValue());
				} else { //Otherwise, if the dragon is awake, it wins
					if(!dragons.get(adjacentDragons.get(i)).isSleeping())
						gameState = GameState.DRAGON_WIN;
				}
			}
		}

		//Update game state
		if (isHeroOnExit() && dragons.size() == 0) {
			gameState = GameState.HERO_WIN;
		}

		//Update dragon and sword characters on maze
		if(!sword.getPickedUp()){
			if (getChar(sword.getPosition()) == 'D' || getChar(sword.getPosition()) == 'd')
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

	private ArrayList<Integer> getAdjacentDragons(Point position) {
		ArrayList<Integer> adjacentDragons = new ArrayList<Integer>();

		for (int i = 0; i < dragons.size(); i++){
			// Checks if there is a dragon adjacent to the position given in the arguments
			if (position.distance(dragons.get(i).getPosition()) <= 1)
				adjacentDragons.add(i);
		}
		return adjacentDragons;
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

	private Direction getValidDragonRandomDirection(Point position){
		Direction direction;
		do{
			direction = Game.getRandomDirection();
		} while (!canMove(position, direction));
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

	public Dragon getDragon(int i) {
		return dragons.get(i);
	}

	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

}
