package game;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;

	private char maze[][]={{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};

	public Maze(){
		hero = new Hero(1,1);
		setChar(hero.getX(), hero.getY(), hero.getChar());	

		dragon = new Dragon(1,3);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());

		sword = new Sword(1, 8);
		setChar(sword.getX(), sword.getY(), sword.getChar());
	}

	public void setChar(int x, int y, char c){
		maze[y][x] = c;
	}

	public void unsetChar(int x, int y){
		maze[y][x] = ' ';
	}

	public char getChar(int x, int y){
		return maze[y][x];
	}

	public void moveHero(Game.Direction direction){
		unsetChar(hero.getX(), hero.getY());
		hero.move(direction);
		setChar(hero.getX(), hero.getY(), hero.getChar());
	}	

	public boolean canMove(int x, int y, Game.Direction direction){
		//TODO check for 'S'
		switch(direction){
		case UP:
			if (getChar(x,y-1)=='X'){
				return false;
			}
			break;
		case DOWN:
			if (getChar(x,y+1)=='X'){
				return false;
			}
			break;
		case RIGHT:
			if (getChar(x+1,y)=='X'){
				return false;
			}
			break;	
		case LEFT:
			if (getChar(x-1,y)=='X'){
				return false;
			}
			break;
		case STAY:
			return true;
			//break;	
		}
		return true;



	}

	public void moveDragon(Game.Direction direction){
		unsetChar(dragon.getX(), dragon.getY());
		dragon.move(direction);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());
	}

	public void pickUpSword(){
		hero.equipSword();
		unsetChar(sword.getX(), sword.getY());
	}

	public void print(){

		for(int i=0; i<maze.length ;i++){
			for (int j=0;j<maze[i].length;j++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}

	}



}
