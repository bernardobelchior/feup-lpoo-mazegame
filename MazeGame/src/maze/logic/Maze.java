package maze.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.Game.*;

public class Maze {
	private Hero hero;
	private ArrayList<Dragon> dragons;
	private ArrayList<Sword> swords;
	private Exit exit;
	private GameState gameState = GameState.RUNNING;
	private GameMode gameMode;
	private char[][] maze;

	/**
	 * Maze constructor with a specific maze and provided game mode.
	 * @param maze Maze array
	 * @param gameMode {@link GameMode} 
	 */
	public Maze(char[][] maze, GameMode gameMode) {
		this.gameMode = gameMode;
		this.maze = maze.clone();
		this.dragons = new ArrayList<Dragon>();
		this.swords = new ArrayList<Sword>();
		readMaze();

		setChar(hero.getPosition(), hero.getChar());
		for(int i = 0; i < dragons.size(); i++){
			setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
		}

		for(int i = 0; i < swords.size(); i++) {
			setChar(swords.get(i).getPosition(), swords.get(i).getChar());
		}

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
				case 'A':
					hero = new Hero(new Point(i, j));
					hero.equipSword();
					setChar(new Point(i, j), ' ');
					break;
				case 'D':
					dragons.add(new Dragon(new Point(i, j)));
					setChar(new Point(i, j), ' ');
					break;
				case 'E':
					swords.add(new Sword(new Point(i, j)));
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

	private char getChar(Point position, Direction direction) {
		switch(direction) {
		case UP:
			return getChar(new Point(position.x, position.y - 1));
		case DOWN:
			return getChar(new Point(position.x, position.y + 1));
		case LEFT:
			return getChar(new Point(position.x - 1, position.y));
		case RIGHT:
			return getChar(new Point(position.x + 1, position.y));
		default:
			return getChar(position);
		}
	}

	/**
	 * Checks if the hero can move in the specified {@link Direction}.
	 * If is possible, it will move the {@link Maze#hero}. 
	 * @param direction {@link Direction} in which to move the hero.
	 * @return <code>true</code> if the hero has moved, <code>false</code> otherwise.
	 */
	public boolean moveHero(Direction direction) {
		if (canMove(hero.getPosition(), direction)) {
			unsetChar(hero.getPosition());
			hero.move(direction);
			setChar(hero.getPosition(), hero.getChar());
			return true;
		}

		return false;
	}

	/**
	 * Checks if the game is running, using the {@link Maze#isGameRunning()} method.
	 * If it is, it will try to move the hero in the specified {@link Direction}.
	 * It will then update the dragons in the maze, using {@link Maze#updateDragons()},
	 * and then update the general game state using {@link Maze#update()}.
	 * @param heroDirection {@link Direction} in which the hero will try to move.
	 * @return {@link Maze#moveHero(Direction)}
	 */
	public boolean nextTurn(Direction heroDirection) {
		if(!isGameRunning())
			return false;

		boolean heroHasMoved = moveHero(heroDirection);
		updateDragons();
		update();
		return heroHasMoved;
	}

	/**
	 * Updates the dragons in the maze depending on the {@link Maze#gameMode}.
	 */
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
						unsetChar(dragons.get(i).getPosition());
						dragons.get(i).setSleeping(false);
						setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
						break;
					default:
						break;
					}
				} else {
					switch(random.nextInt(3)){
					case 0:
						unsetChar(dragons.get(i).getPosition());
						dragons.get(i).setSleeping(true);
						setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
						break;
					case 1:
						unsetChar(dragons.get(i).getPosition());
						moveDragon(i, getValidDragonRandomDirection(dragons.get(i).getPosition()));
						setChar(dragons.get(i).getPosition(), dragons.get(i).getChar());
						break;
					default:
						break;
					}
				}
			}
			break;
		}
	}

	/**
	 * Moves the {@link Dragon} in the {@link Maze#dragons} array 
	 * with the index specified in the direction given.
	 * @param i Index of the dragon
	 * @param direction {@link Direction} in which to move the dragon.
	 */
	public void moveDragon(int i, Direction direction) {
		if(i < dragons.size() && canMove(dragons.get(i).getPosition(), direction)){
			dragons.get(i).move(direction);
			update();
		}
	}

	private boolean canMove(Point position, Direction direction) {
		return  (getChar(position, direction) == ' ' ||
				(getChar(position, direction) == 'S' && dragons.size() == 0) ||
				getChar(position, direction) == 'E');
	}

	/**
	 * Updated the general {@link Maze#gameState}.
	 * Checks if the {@link Maze#hero} can pick up a
	 * {@link Maze#swords} and picks it up if he can.
	 * Checks if there is any {@link Dragon} adjacent
	 * to the {@link Maze#hero} and, if it is, changes
	 * the {@link Maze#gameState} accordingly.
	 */
	public void update() {
		for(Sword sword : swords) {
			if (sword.getPosition().equals(hero.getPosition())) {
				pickUpSword(sword);
				setChar(hero.getPosition(), hero.getChar());
				swords.remove(sword);
				break;
			}
		}

		//Returns an ArrayList of indexes of dragons adjacent to the hero
		ArrayList<Integer> adjacentDragons = getAdjacentDragons(hero.getPosition());

		if (adjacentDragons.size() > 0) {
			//If the hero has a sword equipped, then kill the dragon adjacent to him
			for(int i = adjacentDragons.size() -1 ; i >= 0; i--){
				if(hero.getSwordEquipped()) {
					unsetChar(dragons.get(adjacentDragons.get(i).intValue()).getPosition());
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
		for(Sword sword : swords) {
				if (getChar(sword.getPosition()) == 'D' || getChar(sword.getPosition()) == 'F')
					setChar(sword.getPosition(), 'F');
				else if (getChar(sword.getPosition()) == 'd' || getChar(sword.getPosition()) == 'f')
					setChar(sword.getPosition(), 'f');
				else
					setChar(sword.getPosition(), sword.getChar());
		}
	}

	private void pickUpSword(Sword sword) {
		hero.equipSword();
		unsetChar(sword.getPosition());
		swords.remove(sword);
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

	/**
	 * Gets the current {@link Maze#gameState}
	 * @return {@link Maze#gameState}
	 */
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
	
	/**
	 * Returns the maze array as a formatted string.
	 * @return {@link Maze#maze} as a formated string.
	 */
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

	/**
	 * Gets the hero
	 * @return {@link Maze#hero}
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Gets the {@link Sword} in the {@link Maze#swords} array.
	 * Returns <code>null</code> if the index is invalid.
	 * @param i Index of the sword.
	 * @return {@link Sword} in {@link Maze#swords}, or <code>null</code> 
	 * if the index is invalid.
	 */
	public Sword getSword(int i) {
		if(i >= swords.size())
			return null;

		return swords.get(i);
	}

	/**
	 * Gets {@link Maze#swords}
	 * @return {@link Maze#swords}
	 */
	public ArrayList<Sword> getSwords() {
		return swords;
	}

	/**
	 * Gets the {@link Dragon} in the {@link Maze#dragons} array
	 * with the index given in the arguments. 
	 * Returns <code>null</code> if the index is invalid.
	 * @param i Index
	 * @return {@link Dragon} in {@link Maze#dragons}, or <code>null</code> 
	 * if the index is invalid.
	 */
	public Dragon getDragon(int i) {
		if(i > dragons.size())
			return null;
		
		return dragons.get(i);
	}

	/**
	 * Gets {@link Maze#dragons}
	 * @return {@link Maze#dragons}
	 */
	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

	/**
	 * Gets {@link Maze#maze}
	 * @return {@link Maze#maze}
	 */
	public char[][] getMazeArray() {
		return maze;
	}

	/**
	 * Gets {@link Maze#maze} length.
	 * @return {@link Maze#maze} length
	 */
	public int getMazeDimension() {
		return maze.length;
	}
	
	/**
	 * Returns whether the game is running or not.
	 * @return <code>true</code> if the {@link Maze#gameState} is {@value GameState#RUNNING},
	 * returning <code>false</code> otherwise.
	 */
	public boolean isGameRunning() {
		return gameState == GameState.RUNNING;
	}
}