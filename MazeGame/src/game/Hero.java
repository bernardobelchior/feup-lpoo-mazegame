package game;

public class Hero extends Entity{
	boolean swordEquipped;
	
	public Hero(int x, int y){
		super(x, y);
		swordEquipped = false;
	}
	
	public void equipSword(){
		swordEquipped = true;
	}
	
	public boolean getSwordEquipped(){
		return swordEquipped;
	}
	
	public char getHeroChar(){
		return swordEquipped ? 'A' : 'H';
	}
	
	
}
