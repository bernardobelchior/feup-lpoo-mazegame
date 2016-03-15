package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.RandomMazeGenerator;

public class TestRandomMazeGenerator {

/*	public void printMaze(char[][] maze){

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
		printMaze(rmg.getMaze());
		assertTrue(true);
	}*/

	@Test
	public void testGenerateExit() {
		int size = 21;
		RandomMazeGenerator rmg = new RandomMazeGenerator(size, 1);
		Point exit;
		//100 attempts
		for(int i = 0; i < 100; i++) {
			rmg.initializeVariables();
			rmg.generateExit();
			exit = rmg.getExit();
			assertTrue(((exit.x == 0 || exit.x == size - 1) && exit.y % 2 == 1) 
					|| ((exit.y == 0 || exit.y == size - 1) && exit.x % 2 == 1));
		}
	}

	@Test
	public void testGetValidStartingPosition() {
		int size = 21;
		RandomMazeGenerator rmg = new RandomMazeGenerator(size, 1);
		Point exit;
		for(int i = 0; i < 100; i++) {
			rmg.initializeVariables();
			rmg.generateExit();
			exit = rmg.getExit();
			assertEquals(1, (int) exit.distance(rmg.getValidStartingPosition()));
		}
	}
	
	@Test
	public void testPlaceHero() {
		int size = 21;
		RandomMazeGenerator rmg = new RandomMazeGenerator(size, 1);
		Point exit, hero;
		for(int i = 0; i < 10; i++) {
			hero = null;
			rmg.initializeVariables();
			char[][] maze = rmg.getMaze();
			exit = rmg.getExit();
			
			for(int j = 0; j < maze.length; j++){
				for(int k = 0; k < maze[j].length; k++){
					if(maze[k][j] == 'H'){
						hero = new Point(j, k);
						break;
					}
					if(hero != null)
						break;
				}				
			}
			
			assertTrue(hero.distance(exit) > size/3);
		}
	}
}
