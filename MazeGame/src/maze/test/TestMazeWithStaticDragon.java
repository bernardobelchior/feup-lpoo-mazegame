package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Game.Direction;
import maze.logic.Maze;

public class TestMazeWithStaticDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(Direction.LEFT);
		assertEquals(new Point(2, 1), maze.getHeroPosition());
		}


	
	
	
}
