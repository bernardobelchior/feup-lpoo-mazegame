package maze.logic;

import java.awt.Point;
import java.util.Random;

import maze.logic.Game.*;

public class Maze {
	private Hero hero;
	private Dragon[] dragon;
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
		for(int i=0;i<dragon.length;i++){
			setChar(dragon[i].getPosition(), dragon[i].getChar());
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
					break;
				case 'D':
					for (int k=0; i<dragon.length; i++){
						dragon[k] = new Dragon(new Point(i,j));
					}
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
			update();
		}


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
		for (int i=0;i<dragon.length;i++){
			if(!dragon[i].isAlive())
				return;

			unsetChar(dragon[i].getPosition());

			switch (gameMode){
			case STATIONARY:
				break;
			case RANDOM_MOVEMENT:
				moveDragon(getValidDragonRandomDirection(i), i);
				break;
			case SLEEP_RANDOM_MOVEMENT:
				Random random = new Random();

				if(dragon[i].isSleeping()){
					switch(random.nextInt(2)){
					case 0:
						dragon[i].setSleeping(false);
						break;
					default:
						break;
					}
				} else {
					switch(random.nextInt(3)){
					case 0:
						dragon[i].setSleeping(true);
						break;
					case 1:
						moveDragon(getValidDragonRandomDirection(i),i);
					default:
						break;
					}
				}
				break;
			}

			setChar(dragon[i].getPosition(), dragon[i].getChar());
		}
	}
	public void moveDragon(Direction direction, int i) {
		if(!dragon[i].isAlive())
			return;

		dragon[i].move(direction);
		update();

	}

	private boolean canMove(Point position, Direction direction) {
		boolean canmove = true;
		switch (direction) {
		case UP:
			for (int i=0; i<dragon.length;i++){ 
				if (dragon[i].isAlive()) 
					canmove=false;
			}
			if (canmove==false)
				if (getChar(new Point(position.x, position.y -1)) == 'X' 
				|| getChar(new Point(position.x, position.y -1)) == 'S' && canmove==false)
					return false;
			break;
		case DOWN:
			for (int i=0; i<dragon.length;i++){ 
				if (dragon[i].isAlive()) 
					canmove=false;
			}
			if (canmove==false)
				if (getChar(new Point(position.x, position.y +1)) == 'X' 
				|| getChar(new Point(position.x, position.y +1)) == 'S' && canmove==false)
					return false;
			break;
		case RIGHT:
			for (int i=0; i<dragon.length;i++){ 
				if (dragon[i].isAlive()) 
					canmove=false;
			}
			if (canmove==false)
				if (getChar(new Point(position.x+1, position.y)) == 'X' 
				|| getChar(new Point(position.x+1, position.y)) == 'S' && canmove==false)
					return false;
			break;
		case LEFT:
			for (int i=0; i<dragon.length;i++){ 
				if (dragon[i].isAlive()) 
					canmove=false;
			}
			if (canmove==false)
				if (getChar(new Point(position.x-1, position.y)) == 'X' 
				|| getChar(new Point(position.x-1, position.y)) == 'S' && canmove==false)
					return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}

	public void update() {
		//Checks if the hero is next to a dragon
		if (isHeroNextToDragon()!=-1) {
			//If the hero has a sword equipped, then kill the dragon adjacent to him
			if(hero.getSwordEquipped()){
				dragon[isHeroNextToDragon()].kill();
				unsetChar(dragon[isHeroNextToDragon()].getPosition());
				setChar(hero.getPosition(), hero.getChar());
			} //Otherwise, if the dragon is awake, it wins
			else{
				if(!dragon[isHeroNextToDragon()].isSleeping())
					gameState = GameState.DRAGON_WIN;
			}
		}

		//Update game state
		if (isHeroOnExit() && !dragon[isHeroNextToDragon()].isAlive()) {
			gameState = GameState.HERO_WIN;
		}

		//Update dragon and sword characters on maze
		if(!sword.getPickedUp()){
			if (dragon[isHeroNextToDragon()].getPosition().equals(sword.getPosition()))
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

	private int isHeroNextToDragon() {
		int k=-1;		//k é o inteiro que me vai dizer a posiçao do dragao que está ao pé do heroi no vetor dos dragoes; 
						//se k=-1 quer dizer que o heroi nao está ao pé de nenhum dragao, se for outro numero quer dizer que 
						//o heroi está ao pe de um dragao e diz me a posiçao desse dragao e a posiçao desse dragao no vetor dos dragoes; 
						//por esta funçao a retornar um inteiro em vez de um booleano vai facilitar imenso na funçao update()
		for (int i=0; i<dragon.length;i++){
			if(dragon[i].isAlive()){
				// Checks if the hero is in an adjacent square to the dragon or in the same square
				if ((Math.abs(hero.getX() - dragon[i].getX()) <= 1 && Math.abs(hero.getY() - dragon[i].getY()) == 0)
						|| (Math.abs(hero.getX() - dragon[i].getX()) == 0 && Math.abs(hero.getY() - dragon[i].getY()) == 1)) {
					return k=i;
				}
			}
		}
		return k;
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

	private Direction getValidDragonRandomDirection(int i){
		Direction direction;
		do{
			direction = getRandomDirection();
		} while (!canMove(dragon[i].getPosition(), direction));
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
		return dragon[0];		 //TODO se esta funçao só for chamada na classe do teste em que só há um dragao
									//esse draçao está no indice 0 do vetor dos drages logo o que fiz faz sentido senao isto está mal
								
	}

}
