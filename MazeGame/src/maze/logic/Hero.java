package maze.logic;

import java.awt.Point;

public class Hero extends Entity {
	private boolean swordEquipped;
	
	/**
	 * Hero constructor
	 * @param position Hero's position on the maze array
	 */
	public Hero(Point position){
		super(position);		
		swordEquipped = false;
	}
	
	/**
	 * Sets the {@link Hero#swordEquipped} flag to <code>true</code>.
	 */
	public void equipSword(){
		swordEquipped = true;
		
	}
	
	/**
	 * Gets {@link Hero#swordEquipped}.
	 * @return {@link Hero#swordEquipped}
	 */
	public boolean getSwordEquipped(){
		return swordEquipped;
	}
	
	/**
	 * Gets the character used to represent the hero depending on its state.
	 * @return Returns the character that represents the hero state.
	 */
	public char getChar(){
		return swordEquipped ? 'A' : 'H';	
	}
}
