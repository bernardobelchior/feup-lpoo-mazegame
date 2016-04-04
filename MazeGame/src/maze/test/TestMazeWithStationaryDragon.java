package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Maze;
import maze.logic.Game.Direction;
import maze.logic.Game.GameMode;
import maze.logic.Game.GameState;

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
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.DOWN);
		assertEquals(0, maze.getSwords().size());
		assertEquals(true, maze.getHero().getSwordEquipped());
	}
	
	@Test
	public void testMoveUnarmedHeroNextToDragon() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(GameState.RUNNING, maze.getGameState());
		maze.nextTurn(Direction.DOWN);
		assertEquals(GameState.DRAGON_WIN, maze.getGameState());
	}
	
	@Test
	public void testMoveArmedHeroNextToDragon() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(1, maze.getDragons().size());
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.RIGHT);
		assertEquals(0, maze.getDragons().size());
	}
	
	@Test
	public void testHeroKillsDragonAndExits() {
		Maze maze = new Maze(m1, GameMode.STATIONARY);
		assertEquals(GameState.RUNNING, maze.getGameState());
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.RIGHT);
		maze.nextTurn(Direction.RIGHT);
		maze.nextTurn(Direction.UP);
		maze.nextTurn(Direction.UP);
		maze.nextTurn(Direction.RIGHT);
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
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.LEFT);
		maze.nextTurn(Direction.DOWN);
		maze.nextTurn(Direction.DOWN);
		assertEquals(true, maze.getHero().getSwordEquipped());
		maze.nextTurn(Direction.UP);
		maze.nextTurn(Direction.UP);
		maze.nextTurn(Direction.RIGHT);
		maze.nextTurn(Direction.RIGHT);
		maze.nextTurn(Direction.RIGHT);
		assertEquals(GameState.RUNNING, maze.getGameState());
	}
}
