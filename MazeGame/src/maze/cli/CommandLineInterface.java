package maze.cli;
import java.util.Scanner;
import java.lang.Character;

import maze.logic.Game;

public class CommandLineInterface {
	static private Scanner scanner = new Scanner(System.in);
	
	public CommandLineInterface(){

	}

	public Game.Direction getHeroDirection(){
		System.out.println();
		System.out.println("Where would you like to move?");
		Game.Direction direction = toDirection(scanner.nextLine());
		return direction;
	} 

	private Game.Direction toDirection(String direction){
		switch(Character.toUpperCase(direction.charAt(0))){
		case 'W':
			return Game.Direction.UP;
		case 'S':
			return Game.Direction.DOWN;
		case 'A':
			return Game.Direction.LEFT;
		case 'D':
			return Game.Direction.RIGHT;
		default:
			return Game.Direction.STAY;
		}
	}
	
	public void print(String string){
		System.out.println(string);
	}
}
