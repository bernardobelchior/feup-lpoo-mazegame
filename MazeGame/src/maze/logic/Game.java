package maze.logic;

import java.util.Random;

public class Game {
	public enum Direction { UP, DOWN, RIGHT, LEFT, STAY };
	public enum GameState { RUNNING, DRAGON_WIN, HERO_WIN };
	public enum GameMode { STATIONARY, RANDOM_MOVEMENT, SLEEP_RANDOM_MOVEMENT };
	public enum EntityType { WALL, BLANK, DRAGON_AWAKEN, DRAGON_SLEEPING, HERO_UNARMED, HERO_ARMED, SWORD, EXIT };
	
	/**
	 * Generates and returns a random direction, including {@link Direction#STAY}.
	 * @return A random {@link Direction}.
	 */
	public static Direction getRandomDirection(){
		Random random = new Random();
		int direction = random.nextInt(5);

		switch (direction){
		case 0:
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.RIGHT;
		case 3:
			return Direction.LEFT;
		default:
			return Direction.STAY;
		}
	}
}
	