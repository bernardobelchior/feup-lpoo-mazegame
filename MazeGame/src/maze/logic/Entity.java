package maze.logic;

import java.awt.Point;

import maze.logic.Game.*;

public class Entity {
	private Point position;

	/**
	 * Entity class constructor
	 * @param position Entity's position on the maze array 
	 */
	public Entity(Point position){
		this.position = position;

	}

	/**
	 * Gets the x component of the entity's position
	 * @return {@link Entity#position#x}
	 */
	public int getX(){
		return position.x;
	}

	/**
	 * Gets the y component of the entity's position
	 * @return {@link Entity#position#y}
	 */
	public int getY(){
		return position.y;
	}

	/**
	 * Gets the entity's position
	 * @return {@link Entity#position}
	 */
	public Point getPosition(){
		return position;
	}

	/**
	 * Moves the enity in the specified {@link Direction}.
	 * Should not be used as it does not check for valid moves.
	 * @param direction Direction in which the entity should move.
	 */
	public void move(Direction direction){
		switch(direction){
		case UP:
			position.y--;
			break;
		case DOWN:
			position.y++;
			break;
		case LEFT:
			position.x--;
			break;
		case RIGHT:
			position.x++;
			break;
		case STAY:
			break;
		}
	}
}
