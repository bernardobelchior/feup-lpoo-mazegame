package maze.cli;

import java.util.Random;

import maze.logic.Maze;
import maze.logic.Game.*;

public class GameInterface {
	private Maze maze;
	private CommandLineInterface cli = new CommandLineInterface();
	private char[][] grid;


	public GameInterface(){
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

			cli.print("What mode would you like to play in?");
			cli.print("S for Stationary Dragon.");
			cli.print("R for Random Movement");
			cli.print("Everything else for Sleeping and Random Movement");

			maze = new Maze(grid, cli.getGameMode());
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
		if(!maze.moveHero(cli.getHeroDirection()))
			cli.print("You cannot move in this direction");
		maze.updateDragon();
	}

	private void generateRandomMaze(){
		int side; 
		int nDragons;
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

		//TODO create random walls inside the maze


		//Count the number of blank cells in the maze
		int nBlankCells=0;
		for (int i=0;i<grid.length;i++){
			for (int j=0; j<grid[i].length;j++){
				if (grid[i][j]==' ')
					nBlankCells=nBlankCells+1;
			}
		}


		//Generates Hero
		do{
			x = random.nextInt(side-2)+1;
			y = random.nextInt(side-2)+1;
		}
		while (grid[y][x]!=' ');
		grid[y][x] = 'H';


		//Generates Dragons
		nDragons=random.nextInt(nBlankCells/2)+1;
		for (int i=1;i==nBlankCells;i++){
			do{
				x = random.nextInt(side-2)+1;
				y = random.nextInt(side-2)+1;
			}
			while (grid[y][x]!=' ');
			grid[y][x] = 'D';
		}


		//Generates Sword
		do{
			x = random.nextInt(side-2)+1;
			y = random.nextInt(side-2)+1;
		}
		while (grid[y][x]!=' ');
		grid[y][x] = 'E';

		//Generates Exit
		do{
			x = random.nextInt(side);
			y = random.nextInt(side);	
		} while(((x == 0 || x == side-1) && (y == 0 || y == side -1)) || 	//Checks if the exit is on the corner
				((x != 0 && x != side -1) && (y != 0 && y != side-1))); 	//Checks if the exit is in the middle of the maze
		grid[y][x] = 'S';
	}
}
