package maze.cli;

import maze.logic.Maze;
import maze.logic.RandomMazeGenerator;
import maze.logic.Game.*;

public class GameInterface {
	private Maze maze;
	private CommandLineInterface cli = new CommandLineInterface();

	public GameInterface(){
		//TODO Grid is temporary until we figure out how to place dragons randomly
			char[][] grid = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' }, 
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
				{ 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
			cli.print("How long do you want the maze to be? (Odd numbers only)");
			
			RandomMazeGenerator rmg = new RandomMazeGenerator(cli.getInt(), 1);
			
			cli.print("What mode would you like to play in?");
			cli.print("S for Stationary Dragons.");
			cli.print("R for Random Movement");
			cli.print("Everything else for Sleeping and Random Movement");

			maze = new Maze(grid, cli.getGameMode());
			//maze = new Maze(rmg.getMaze(), cli.getGameMode());
	}

	public void play(){	
		while(maze.getGameState() == GameState.RUNNING){
			cli.print(maze.toString());
			nextTurn();
		}

		cli.print(maze.toString());

		if(maze.getGameState() == GameState.DRAGON_WIN){
			cli.print("The dragon has won!");
		} else {
			cli.print("You have won!");
		}
	}

	private void nextTurn(){
		if(!maze.nextTurn(cli.getHeroDirection()))
			cli.print("You cannot move in this direction");
	}
}
