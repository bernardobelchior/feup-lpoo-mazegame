package maze.cli;

import maze.logic.Maze;

import javax.swing.JFrame;

import maze.logic.Game.*;

public class GameInterface {
	private Maze maze;
	private CommandLineInterface cli = new CommandLineInterface();

	public GameInterface(JFrame parent, Maze maze) {
		this.maze = maze;
		
		cli.print("Use the WASD keys to move the hero\n.");
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
