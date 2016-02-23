package game;

public class GameLogic {
	private Maze maze;

	
	public GameLogic(){
		maze = new Maze();
		
		print();
	}
	
	public void print(){
		maze.print();
	}
}
