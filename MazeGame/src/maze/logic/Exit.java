package maze.logic;

import java.awt.Point;

public class Exit extends Entity {
	
	public Exit(Point position){
		super(position);
	}
	
	public char getChar(){
		return 'S';
	}
}
