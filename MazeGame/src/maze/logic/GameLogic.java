package maze.logic;

import maze.cli.CommandLineInterface;

enum GameState { RUNNING, DRAGON_WIN, HERO_WIN };

public class GameLogic {
	private Maze maze;
	private CommandLineInterface cli;

	public GameLogic(){
		maze = new Maze();
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
