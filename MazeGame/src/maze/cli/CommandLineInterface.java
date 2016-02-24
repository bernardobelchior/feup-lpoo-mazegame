package maze.cli;
import java.util.Scanner;

import maze.logic.Game;

public class CommandLineInterface {

	public CommandLineInterface(){

	}

	public Game.Direction getHeroDirection(){
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Where would you like to move?");
		Game.Direction direction = toDirection(scanner.nextLine());
		scanner.close();
		return direction;
	} 

	private Game.Direction toDirection(String direction){
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
	
	public void print(String string){
		System.out.println(string);
	}
}
