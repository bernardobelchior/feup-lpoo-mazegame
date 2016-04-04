package maze.logic;

import java.awt.Point;

public class Exit extends Entity {
	
	/**
	 * Exit constructor
	 * @param position Exit's position on the maze array
	 */
	public Exit(Point position){
		super(position);
	}
	
	/**
	 * Gets the exit character to be put on the maze array
	 * @return Exit character to be put on the maze array
	 */
	public char getChar(){
		return 'S';
	}
}
