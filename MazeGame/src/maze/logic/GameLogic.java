package maze.logic;

import java.util.Random;

import maze.cli.CommandLineInterface;
import maze.logic.Game.*;

public class GameLogic {
	private Maze maze;
	private CommandLineInterface cli = new CommandLineInterface();
	private char[][] grid;
	
	public GameLogic(){
		grid = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						  	  { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
						  	  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  	  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
						  	  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  	  { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ' }, 
						  	  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
						  	  { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, 
						  	  { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
						  	  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		generateRandomMaze();
		maze = new Maze(grid);
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
	
	private void generateRandomMaze(){
		int side; 
		Random random = new Random();
		
		cli.print("We are generating a NxN maze.");
		cli.print("Please introduce your N:");
		side = cli.getInt();
		
		while(side < 6){
			cli.print("Maze too small.");
			cli.print("Please introduce your N:");
			side = cli.getInt();
		}
		
		grid = new char[side][side];
		
		//Fill with spaces
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				grid[j][i] = ' ';
			}
		}
		
		//Creates horizontal walls
		for(int i = 0; i < grid.length; i++){
			grid[i][0] = 'X';
			grid[i][grid.length-1] = 'X';
		}
		
		//Creates vertical walls
		for(int i = 1; i < grid.length-1; i++){
			grid[0][i] = 'X';
			grid[grid.length-1][i] = 'X';
		}
		
		int x,y;
		
		//Generates Hero
		x = random.nextInt(side-2)+1;
		cli.print(Integer.toString(x));
		y = random.nextInt(side-2)+1;
		cli.print(Integer.toString(y));
		grid[y][x] = 'H';
		
		//Generates Dragon
		x = random.nextInt(side-2)+1;
		cli.print(Integer.toString(x));
		y = random.nextInt(side-2)+1;
		cli.print(Integer.toString(y));
		grid[y][x] = 'D';
		
		//Generates Sword
		x = random.nextInt(side-2)+1;
		cli.print(Integer.toString(x));
		y = random.nextInt(side-2)+1;
		cli.print(Integer.toString(y));
		grid[y][x] = 'E';
		
		//Generates Exit
		do{
			x = random.nextInt(side);
			y = random.nextInt(side);	
		} while((x != 0 && x != side-1 && y != 0 && y != side-1) &&
				((x == 0 && (y == 0 || y == side-1)) || (x == side-1 && (y == 0 || y == side-1)))); //Not working
		cli.print(Integer.toString(x));
		cli.print(Integer.toString(y));
		grid[y][x] = 'S';
	}
}
