package maze.logic;

import java.awt.Point;

public class Hero extends Character {
	private boolean swordEquipped;
	
	public Hero(Point position){
		super(position);		
		swordEquipped = false;
	}
		
	public void equipSword(){
		swordEquipped = true;
		
	}
	
	public boolean getSwordEquipped(){
		return swordEquipped;
	}
	
	
	public char getChar(){
		return swordEquipped ? 'A' : 'H';	
	}
}
