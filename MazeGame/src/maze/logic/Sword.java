package maze.logic;		//TODO explicar margarida os packages

public class Sword {
	private int x, y;	
	private boolean pickedUp;

	public Sword(int x, int y){
		this.x = x;
		this.y = y;
		this.pickedUp = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
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
