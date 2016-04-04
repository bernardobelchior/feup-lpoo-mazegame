package maze.logic;		

import java.awt.Point;

public class Sword extends Entity {	
	/**
	 * Sword constructor.
	 * @param position Sword position on the maze array.
	 */
	public Sword(Point position){
		super(position);
	}
	
	/**
	 * Gets the char that represents the {@link Sword} on the maze array.
	 * @return {@link Sword} character.
	 */
	public char getChar(){
		return 'E';
	}
}
