package game;

public class Entity {
	int x,y;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void move(Game.Direction direction){
		switch(direction){
		case UP:
			if(y > 0)
				y--;
			break;
		case DOWN:
			//if(y < )
			y++;
			break;
		case LEFT:
			if(x > 0)
				x--;
			break;
		case RIGHT:
			//if(x < )
			x++;
			break;
		case STAY:
			break;
		}
	}
}
