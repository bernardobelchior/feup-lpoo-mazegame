package maze.cli;

import maze.logic.Maze;

import javax.swing.JFrame;

import maze.logic.Game.*;

public class GameInterface {
	private Maze maze;
	private CommandLineInterface cli = new CommandLineInterface();
	private JFrame parent;

	public GameInterface(JFrame parent, Maze maze) {
		this.maze = maze;
		this.parent = parent;
		
		play();
	}
	
	public GameInterface(){
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
						
			cli.print("What mode would you like to play in?");
			cli.print("S for Stationary Dragons.");
			cli.print("R for Random Movement");
			cli.print("Everything else for Sleeping and Random Movement");

			maze = new Maze(grid, cli.getGameMode());
			play();
			
			parent.setVisible(true);
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
