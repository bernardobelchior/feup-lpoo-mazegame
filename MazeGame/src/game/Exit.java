package game;

public class Exit {
	private int x,y;
	
	public Exit(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public char getChar(){
		return 'S';
	}
}
