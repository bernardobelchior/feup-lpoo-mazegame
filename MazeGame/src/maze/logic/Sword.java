package maze.logic;		

import java.awt.Point;

public class Sword extends Entity {	
	private boolean pickedUp;

	public Sword(Point position){
		super(position);
		this.pickedUp = false;
	}
	
	public char getChar(){
		return 'E';
	}
	
	public void pickUp(){
		pickedUp = true;
	}
	
	public boolean getPickedUp(){
		return pickedUp;
	}

}
