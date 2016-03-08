package maze.logic;

import java.awt.Point;

import maze.cli.Game.*;

public class Entity {
	private Point position;

	public Entity(Point position){
		this.position = position;

	}

	public int getX(){
		return position.x;
	}

	public int getY(){
		return position.y;
	}

	public Point getPosition(){
		return position;
	}

	public void move(Direction direction){
		switch(direction){
		case UP:
			position.y--;
			break;
		case DOWN:
			position.y++;
			break;
		case LEFT:
			position.x--;
			break;
		case RIGHT:
			position.x++;
			break;
		case STAY:
			break;
		}
	}
}
