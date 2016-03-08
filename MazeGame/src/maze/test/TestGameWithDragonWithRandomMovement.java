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
		assertEquals(new Point(3,3), maze.getDragon(0).getPosition());
	}
}
