package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.cli.Game.Direction;
import maze.cli.Game.GameMode;
import maze.cli.Game.GameState;
import maze.logic.Maze;

public class TestMazeWithStationaryDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.LEFT);
		assertEquals(new Point(2, 1), maze.getHero().getPosition());
		}
	
	@Test
	public void testMoveHeroToFullCell() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.UP);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
	}
	
	@Test
	public void testMoveHeroToSwordAndEquipsIt() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
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
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(GameState.RUNNING, maze.getGameState());
		maze.moveHero(Direction.DOWN);
		assertEquals(GameState.DRAGON_WIN, maze.getGameState());
	}
	
	@Test
	public void testMoveArmedHeroNextToDragon() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(1, maze.getDragons().size());
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.LEFT);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.DOWN);
		maze.moveHero(Direction.RIGHT);
		assertEquals(0, maze.getDragons().size());
	}
	
	@Test
	public void testHeroKillsDragonAndExits() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
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
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(false, maze.getHero().getSwordEquipped());
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(Direction.RIGHT);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
	}
	
	@Test
	public void testMoveArmedHeroToExitButDragonIsAlive() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(false, maze.getHero().getSwordEquipped());
		assertEquals(1, maze.getDragons().size());
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
		maze.updateDragons();
		assertEquals(GameState.RUNNING, maze.getGameState());
	}
}
