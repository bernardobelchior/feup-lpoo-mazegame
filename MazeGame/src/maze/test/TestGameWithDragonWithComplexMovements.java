package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Maze;
import maze.logic.Game.Direction;
import maze.logic.Game.GameMode;

public class TestGameWithDragonWithComplexMovements {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

//@Test
public void testMoveDragon() {
Maze maze = new Maze(m1, GameMode.RANDOM_MOVEMENT);
assertEquals(new Point(3,2), maze.getDragon(0).getPosition());
maze.moveHero(Direction.LEFT);
/*maze.moveDragon(Direction.LEFT);
assertEquals(new Point(3,2), maze.getDragon().getPosition());
}

*/
}
}
