package game;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean can;

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

		canMove(hero.getX(),hero.getY(),direction);
		if (can==true){
			unsetChar(hero.getX(), hero.getY());
			hero.move(direction);
			setChar(hero.getX(), hero.getY(), hero.getChar());
			hero.
			
			
			hero.NextToDragon();	//sei porque é que está mal mas estava demasiado cansada para mudar o metodo de classe
			if (hero.getnexttodragon()==true){
				if (hero.getSwordEquipped()==true){
					dragon.dead();
				}
				else 
					hero.Dead();
				System.out.println("you just died! GAME OVER!!!");
			}
		}
		else 
			System.out.println("you cannot move in this direction");
	}	

	public void moveDragon(Game.Direction direction){
		canMove(dragon.getX(),dragon.getY(),direction);
		if (can==true){
			unsetChar(dragon.getX(), dragon.getY());
			dragon.move(direction);
			setChar(dragon.getX(), dragon.getY(), dragon.getChar());
		}
		System.out.println("you cannot move in this direction");
	}


	public boolean canMove(int x, int y, Game.Direction direction){

		switch(direction){

		case UP:
			switch (getChar(x,y-1)){	//TODO fazer isto para todos os cases das direçoes
			case 'X':
				return can=false;
				break;
			case 'S':
				if (dragon.getLife()==false && hero.getChar()=='A'){
					System.out.println("you won the game!!!");
					return can=true;
				}
				else 
					return can=false;
				break;
			}


		case DOWN:
			if (getChar(x,y+1)=='X'){
				return can=false;
			}
			break;


		case RIGHT:
			if (getChar(x+1,y)=='X'){
				return can=false;
			}
			break;	


		case LEFT:
			if (getChar(x-1,y)=='X'){
				return can=false;
			}
			break;


		case STAY:
			return can=true;
			break;	
		}
		can=true;
		return can;	//PARA QUE SERVE ISTO????



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
