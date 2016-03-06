package maze.logic;

import java.awt.Point;

public class Dragon extends Character {
	private boolean sleeping;
	

	public Dragon(Point position){
		super(position);
		sleeping = false;
	}

		
	public char getChar(){
		return sleeping ? 'd' : 'D';
	}

	public boolean isSleeping(){
		return sleeping;
	}

	public void setSleeping(boolean sleeping){
		this.sleeping = sleeping;
	}
}
