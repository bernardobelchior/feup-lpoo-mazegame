package game;

public class Game {
	public enum Direction { UP, DOWN, RIGHT, LEFT, STAY	}
	
	public static void main(String[] args) {
		Maze maze=new Maze();

		maze.print();
		
	}

}
	