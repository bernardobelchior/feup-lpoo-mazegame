package maze.logic;

public class Game {
	public enum Direction { UP, DOWN, RIGHT, LEFT, STAY	}
	
	public static void main(String[] args) {
		GameLogic gameLogic = new GameLogic();

		gameLogic.play();		
	}
}
	