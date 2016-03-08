package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Maze;
import maze.logic.Game;
import maze.logic.Game.Direction;
import maze.logic.Game.GameMode;

public class TestGameWithDragonWithRandomMovement {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testMoveDragonToFullCell() {
		Maze maze = new Maze(m1, GameMode.RANDOM_MOVEMENT);
		Direction direction;

		assertEquals(new Point(3,3), maze.getDragon(0).getPosition());
		do {
			direction = Game.getRandomDirection();
		} while(direction != Direction.DOWN && direction != Direction.RIGHT);
		maze.moveDragon(0, direction);
		assertEquals(new Point(3,3), maze.getDragon(0).getPosition());
	}
	
	@Test
	public void testMoveDragonToFreeCell() {
		Maze maze = new Maze(m1, GameMode.RANDOM_MOVEMENT);
		Direction direction;

		assertEquals(new Point(3,3), maze.getDragon(0).getPosition());
		do {
			direction = Game.getRandomDirection();
		} while(direction != Direction.UP && direction != Direction.LEFT);
		maze.moveDragon(0, direction);
		if(direction == Direction.UP)
			assertEquals(new Point(3,2), maze.getDragon(0).getPosition());
		else
			assertEquals(new Point(2,3), maze.getDragon(0).getPosition());
	}
	
	@Test
	public void testDragonFallsAsleep() {
		Maze maze = new Maze(m1, GameMode.SLEEP_RANDOM_MOVEMENT);
		
		assertEquals(false, maze.getDragon(0).isSleeping());
		do {
			maze.updateDragons();
		} while(!maze.getDragon(0).isSleeping());
		assertEquals(true, maze.getDragon(0).isSleeping());
	}
	
	@Test
	public void testDragonWakesUp() {
		Maze maze = new Maze(m1, GameMode.SLEEP_RANDOM_MOVEMENT);
		
		maze.getDragon(0).setSleeping(true);
		assertEquals(true, maze.getDragon(0).isSleeping());
		do {
			maze.updateDragons();
		} while(maze.getDragon(0).isSleeping());
		assertEquals(false, maze.getDragon(0).isSleeping());
	}
}
