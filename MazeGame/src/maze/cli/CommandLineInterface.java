package maze.cli;

import java.util.Scanner;
import java.lang.Character;
import maze.logic.Game.*;

public class CommandLineInterface {
	static private Scanner scanner = new Scanner(System.in);
	
	public CommandLineInterface(){

	}

	public Direction getHeroDirection(){
		System.out.println();
		System.out.println("Where would you like to move?");
		return toDirection(scanner.nextLine());
	} 

	private Direction toDirection(String direction){
		switch(Character.toUpperCase(direction.charAt(0))){
		case 'W':
			return Direction.UP;
		case 'S':
			return Direction.DOWN;
		case 'A':
			return Direction.LEFT;
		case 'D':
			return Direction.RIGHT;
		default:
			return Direction.STAY;
		}
	}
	
	public GameMode getGameMode(){
		switch(Character.toUpperCase(scanner.nextLine().charAt(0))){
		case 'S':
			return GameMode.STATIONARY;
		case 'R':
			return GameMode.RANDOM_MOVEMENT;
		default:
			return GameMode.SLEEP_RANDOM_MOVEMENT;
		}
	}
	
	public int getInt(){
		int result = scanner.nextInt();
		scanner.nextLine();
		return result;
	}
	
	public void print(String string){
		System.out.println(string);
	}
}
