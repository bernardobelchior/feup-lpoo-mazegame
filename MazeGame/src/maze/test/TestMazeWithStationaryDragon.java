package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Game.Direction;
import maze.logic.Game.GameMode;
import maze.logic.Game.GameState;
import maze.logic.Maze;

public class TestMazeWithStationaryDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.LEFT);
		assertEquals(new Point(2, 1), maze.getHero().getPosition());
		}
	
	@Test
	public void testMoveHeroToFullCell() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.UP);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
	}
	
	@Test
	public void testMoveHeroToSwordAndEquipsIt() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(false, maze.getHero().getSwordEquipped());
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.DOWN);
		assertEquals(maze.getSword().getPosition(), maze.getHero().getPosition());
		assertEquals(true, maze.getHero().getSwordEquipped());
	}
	
	@Test
	public void testMoveUnarmedHeroNextToDragon() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(GameState.RUNNING, maze.getGameState());
		maze.moveHero(Direction.DOWN);
		assertEquals(GameState.DRAGON_WIN, maze.getGameState());
	}
	
	@Test
	public void testMoveArmedHeroNextToDragon() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(true, maze.getDragon().isAlive());
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.RIGHT);
		assertEquals(false, maze.getDragon().isAlive());
	}
	
	@Test
	public void testHeroKillsDragonAndExits() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(GameState.RUNNING, maze.getGameState());
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.RIGHT);
		maze.moveHero(Direction.RIGHT);
		maze.moveHero(Direction.UP);
		maze.moveHero(Direction.UP);
		maze.moveHero(Direction.RIGHT);
		assertEquals(GameState.HERO_WIN, maze.getGameState());
	}
	
	@Test
	public void testMoveUnarmedHeroToExit() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(false, maze.getHero().getSwordEquipped());
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.RIGHT);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
	}
	
	@Test
	public void testMoveArmedHeroToExitButDragonIsAlive() {
		Maze maze = new Maze(m1, true, GameMode.STATIONARY);
		assertEquals(false, maze.getHero().getSwordEquipped());
		assertEquals(true, maze.getDragon().isAlive());
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.DOWN);
		assertEquals(true, maze.getHero().getSwordEquipped());
		maze.moveHero(Direction.UP);
		maze.moveHero(Direction.UP);
		maze.moveHero(Direction.RIGHT);
		maze.moveHero(Direction.RIGHT);
		maze.moveHero(Direction.RIGHT);
		assertEquals(GameState.RUNNING, maze.getGameState());
	}
}
