package maze.logic;

public class Dragon extends Entity {
	private boolean sleeping;

	public Dragon(int x, int y){
		super(x,y);
		sleeping = false;
	}

	public char getChar(){
		return sleeping ? 'd' : 'D';
	}

	public boolean isSleeping(){
		return sleeping;
	}

	public void setSleeping(boolean sleeping){
		this.sleeping = sleeping;
	}
}
