package maze.logic;

import maze.logic.Game.*;

public class Entity {
	private int x,y;
	private boolean alive;

	public Entity(int x, int y){
		this.x = x;
		this.y = y;
		this.alive = true;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void move(Direction direction){
		switch(direction){
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		case STAY:
			break;
		}
	}

	public boolean isAlive(){
		return alive;
	}

	public void kill(){
		alive = false;
	}

}
