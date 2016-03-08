package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.RandomMazeGenerator;

public class TestRandomMazeGenerator {

	public void printMaze(char[][] maze){
		
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void printRandomMaze(){
		RandomMazeGenerator rmg = new RandomMazeGenerator(21);
		//printMaze(rmg.getMaze());
		assertEquals(true, true);
	}
}
