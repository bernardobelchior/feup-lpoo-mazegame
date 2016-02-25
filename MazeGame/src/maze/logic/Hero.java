package maze.logic;		

public class Hero extends Entity{
	private boolean swordEquipped;
	
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
	
	
	public char getChar(){
		return swordEquipped ? 'A' : 'H';	
	}
	
	/*public boolean NextToDragon(){
		if ((dragon.getX()==x+1 && dragon.getY()==y)||(dragon.getX()==x-1 && dragon.getY()==y)||
			(dragon.getX()==x && dragon.getY()==y+1)||(dragon.getX()==x && dragon.getY()==y+1)){
			 nexttodragon=true;
		}
		return nexttodragon;
	}*/	
}
