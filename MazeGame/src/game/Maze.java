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
	
	public void moveHero(Game.Direction direction){
		
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
