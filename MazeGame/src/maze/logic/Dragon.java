package maze.logic;

import java.awt.Point;

public class Dragon extends Entity {
	private boolean sleeping;
	
	/**
	 * Dragon constructor
	 * @param position Dragon's position on the maze array
	 */
	public Dragon(Point position){
		super(position);
		sleeping = false;
	}

	/**
	 * Gets the character used to represent the dragon depending on its state.
	 * @return Returns the character that represents the dragon state.
	 */
	public char getChar(){
		return sleeping ? 'd' : 'D';
	}

	/**
	 * Returns true if the dragon is sleeping, returning false otherwise.
	 * @return Returns the {@link Dragon#sleeping} flag.  
	 */
	public boolean isSleeping(){
		return sleeping;
	}

	/**
	 * Sets the {@link Dragon#sleeping} flag according to its parameter.
	 * @param sleeping New {@link Dragon#sleeping} flag value. 
	 */
	public void setSleeping(boolean sleeping){
		this.sleeping = sleeping;
	}
}
