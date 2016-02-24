package game;

public class Game {
	public enum Direction { UP, DOWN, RIGHT, LEFT, STAY	}
	
	public static void main(String[] args) {
		GameLogic gameLogic = new GameLogic();

		gameLogic.play();		
	}
		//TODO explicar a margarida todas as duvidas que est�o em comentario espalhadas pelo c�digo
}
	