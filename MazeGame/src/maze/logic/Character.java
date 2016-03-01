package maze.logic;

import java.awt.Point;

public class Character extends Entity {
	private boolean alive;
	
	public Character(Point position){
		super(position);
		this.alive = true;
	}
	
	public boolean isAlive(){
		return alive;
	}

	public void kill(){
		alive = false;
	}
}
