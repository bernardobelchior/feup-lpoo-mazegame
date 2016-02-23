package game;		//O QUE É ISTO??

public class Hero extends Entity{
	boolean swordEquipped;
	
	public Hero(int x, int y){
		super(x, y);		//O QUE É ISTO??
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
