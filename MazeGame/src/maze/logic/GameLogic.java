package maze.logic;

import maze.cli.CommandLineInterface;
import maze.logic.Game.*;

public class GameLogic {
	private Maze maze;
	private CommandLineInterface cli;

	public GameLogic(){
		char grid[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						  { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
						  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
						  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ' }, 
						  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
						  { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
						  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		
		maze = new Maze(grid);
		cli = new CommandLineInterface();
	}

	public void play(){	
		while(maze.getGameState() == GameState.RUNNING){
			cli.print(maze.toString());
			maze.nextTurn();
		}
		
		cli.print(maze.toString());
		
		if(maze.getGameState() == GameState.DRAGON_WIN){
			cli.print("The dragon has won!");
		} else {
			cli.print("You have won!");
		}
	}
}
