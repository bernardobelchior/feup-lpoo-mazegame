package maze.logic;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;

	private char maze[][]={{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ',' '},
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

		exit = new Exit(9, 5);
		setChar(exit.getX(), exit.getY(), exit.getChar());
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
		if (canMove(hero.getX(),hero.getY(),direction)){
			unsetChar(hero.getX(), hero.getY());
			hero.move(direction);
			if(!sword.getPickedUp() && sword.getX() == hero.getX() && sword.getY() == hero.getY()){
				pickUpSword();
			}
			setChar(hero.getX(), hero.getY(), hero.getChar());
		}
		else 
			System.out.println("You cannot move in this direction");
	}	

	public void moveDragon(Game.Direction direction){

		if (canMove(dragon.getX(),dragon.getY(),direction)){
			unsetChar(dragon.getX(), dragon.getY());
			dragon.move(direction);
			setChar(dragon.getX(), dragon.getY(), dragon.getChar());
		} else {
			System.out.println("You cannot move in this direction");
		}
	}


	public boolean canMove(int x, int y, Game.Direction direction){

		switch(direction){

		case UP:
			if(getChar(x, y-1) == 'X' || (getChar(x, y-1) == 'S' && dragon.isAlive()))
				return false;
			break;
		case DOWN:
			if(getChar(x, y+1) == 'X' || (getChar(x, y+1) == 'S' && dragon.isAlive()))
				return false;
			break;	
		case RIGHT:
			if(getChar(x+1, y) == 'X' || (getChar(x+1, y) == 'S' && dragon.isAlive()))
				return false;
			break;	
		case LEFT:
			if(getChar(x-1, y) == 'X' || (getChar(x-1, y) == 'S' && dragon.isAlive()))
				return false;
			break;
		case STAY:
			return true;
		}
		return true;	//PARA QUE SERVE ISTO???? O que? o return?
	}

	public void update(){
		if(isHeroNextToDragon() && hero.getSwordEquipped()){
			dragon.kill();
			unsetChar(dragon.getX(), dragon.getY());
		}
	}
	
	public void pickUpSword(){
		hero.equipSword();
		sword.pickUp();
		unsetChar(sword.getX(), sword.getY());
	}

	public boolean isHeroNextToDragon(){
		//Checks if the hero is in an adjacent square to the dragon
		if((Math.abs(hero.getX() - dragon.getX()) == 1 && Math.abs(hero.getY() - dragon.getY()) == 0) ||
				(Math.abs(hero.getX() - dragon.getX()) == 0 && Math.abs(hero.getY() - dragon.getY()) == 1)){
			return true;
		}
		return false;
	}

	public boolean isHeroOnExit(){
		if(hero.getX() == exit.getX() && hero.getY() == exit.getY()){
			return true;
		}
		return false;
	}

	public Entity checkGameState(){
		if(isHeroOnExit() && !dragon.isAlive()){
			return hero;
		}
		
		if(isHeroNextToDragon() && !hero.getSwordEquipped()){
			return dragon;
		}
		
		return null;
	}

	public void print(){
		System.out.println(toString());
	}

	public String toString(){
		String result = "";
		for(int i=0; i<maze.length ;i++){
			for (int j=0;j<maze[i].length;j++){
				result += maze[i][j];
			}
			result += '\n';
		}
		
		return result;
	}

}
