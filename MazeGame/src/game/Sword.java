package game;

public class Sword {
	private int x, y;	

	public Sword(int x, int y){
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
		return 'E';
	}

}
