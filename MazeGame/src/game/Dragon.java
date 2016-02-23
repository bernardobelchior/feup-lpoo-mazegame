package game;

public class Dragon extends Entity {

	private boolean life;

	public Dragon(int x, int y){
		super(x,y);
		life=true;
	}

	public char getChar(){
		return 'D';
	}

	public boolean getLife(){
		return life;
	}

	public boolean dead(){
		return life=false;
	}
}
