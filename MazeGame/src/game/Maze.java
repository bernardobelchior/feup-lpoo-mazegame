package game;

public class Maze {

	char maze[][]={{'X','X','X','X','X','X','X','X','X','X'},
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
