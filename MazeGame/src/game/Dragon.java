package game;

public class Dragon extends Entity {
	
	private boolean life=true;
	
	public Dragon(int x, int y){
		super(x,y);
	}
	
	public char getChar(){
		return 'D';
	}
	
	public boolean getLife(){
		return life;
	}
	
	public boolean dead(){
		life=false;
		return life;
	}
}
