package maze.logic;



public class Game {
	public enum Direction { UP, DOWN, RIGHT, LEFT, STAY };
	public enum GameState { RUNNING, DRAGON_WIN, HERO_WIN };
	public enum GameMode { STATIONARY, RANDOM_MOVEMENT, SLEEP_RANDOM_MOVEMENT };
	
	public static void main(String[] args) {	
		GameLogic gameLogic = new GameLogic(false);

		gameLogic.play();		
	}
}
	