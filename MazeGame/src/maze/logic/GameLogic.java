package maze.logic;

import maze.cli.CommandLineInterface;

public class GameLogic {
	private Maze maze;
	private CommandLineInterface cli;

	public GameLogic(){
		maze = new Maze();
		cli = new CommandLineInterface();
	}

	public void play(){
		Entity gameState;
		
		while((gameState = maze.checkGameState()) == null){
			cli.print(maze.toString());
			maze.moveHero(cli.getHeroDirection());
			maze.update();
		}
	
		if(gameState instanceof Dragon){
			cli.print("The dragon has won!");
		} else {
			cli.print("You have won!");
		}
	}
}
