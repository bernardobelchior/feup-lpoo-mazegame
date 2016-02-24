package game;

import java.util.Scanner;

public class GameLogic {
	private Maze maze;


	public GameLogic(){
		maze = new Maze();
	}

	private Game.Direction getDirection(String direction){
		switch(direction){
		case "W":
			return Game.Direction.UP;
		case "S":
			return Game.Direction.DOWN;
		case "A":
			return Game.Direction.LEFT;
		case "D":
			return Game.Direction.RIGHT;
		default:
			return Game.Direction.STAY;
		}
	}

	public void play(){
		String input;
		Scanner scanner = new Scanner(System.in);
		Entity gameState;
		
		while((gameState = maze.checkGameState()) == null){
			maze.print();
			System.out.println();
			System.out.println("Where would you like to move?");
			input = scanner.nextLine();
			maze.moveHero(getDirection(input));
		}
		
		if(gameState instanceof Dragon){
			System.out.println("The dragon has won!");
		} else {
			System.out.println("You have won!");
		}
	}
}
