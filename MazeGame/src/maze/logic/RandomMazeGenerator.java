package maze.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import maze.logic.Game.Direction;

public class RandomMazeGenerator {
	boolean[][] visitedCells;
	char[][] maze;
	Stack<Point> pathHistory;
	Point exit;
	int size;

	public RandomMazeGenerator(int size){
		this.size = size;
		if(this.size % 2 == 0)
			this.size++;

		visitedCells = new boolean[size][size];
		maze = new char[size][size];
		pathHistory = new Stack<Point>();
	}

	private void initializeVariables(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				visitedCells[i][j] = false;
				maze[i][j] = 'X';
			}
		}

		for(int i = 1; i < size; i+=2){
			for(int j = 1; j < size; j+=2){
				maze[i][j] = ' ';
			}
		}
	}

	private void generateExit(){
		Random random = new Random();
		int x,y;

		/*do {
			x = random.nextInt(size);
			y = random.nextInt(size);
		} while( ((x != 0 && x != size-1) && y % 2 == 0) 
				|| ((y != 0 && y != size-1) && x % 2 == 0));*/

		while(true){
			x = random.nextInt(size);
			y = random.nextInt(size);
			if(((x == 0 || x == size - 1) && y % 2 == 1) || ((y == 0 || y == size - 1) && x % 2 == 1))
				break;
		}

		exit = new Point(x, y);
		maze[exit.y][exit.x] = 'S';
	}

	private Point getValidStartingPosition() {
		if(exit.x + 1 < size && maze[exit.y][exit.x + 1] == ' ')
			return new Point(exit.x + 1, exit. y);
		if(exit.y + 1 < size && maze[exit.y + 1][exit.x] == ' ')
			return new Point(exit.x, exit. y + 1);
		if(exit.x - 1 >= 0 && maze[exit.y][exit.x - 1] == ' ')
			return new Point(exit.x - 1, exit. y);
		if(exit.y - 1 >= 0 && maze[exit.y - 1][exit.x] == ' ')
			return new Point(exit.x, exit. y - 1);

		return null;
	}

	private Direction getValidRandomDirection(Point position) {
		ArrayList<Direction> validDirections = new ArrayList<Direction>();
		Random random = new Random();

		if(position.y - 2 > 0 && !visitedCells[position.y - 2][position.x]) 
			validDirections.add(Direction.UP);

		if(position.y + 2 < size && !visitedCells[position.y + 2][position.x])
			validDirections.add(Direction.DOWN);

		if(position.x + 2 < size && !visitedCells[position.y][position.x + 2])
			validDirections.add(Direction.RIGHT);

		if(position.x - 2 > 0 && !visitedCells[position.y][position.x - 2])
			validDirections.add(Direction.LEFT);

		if(validDirections.size() == 0)
			return null;

		return validDirections.get(random.nextInt(validDirections.size()));
	}

	public char[][] getMaze() {
		initializeVariables();
		generateExit();

		Point position = getValidStartingPosition();

		if(position == null)
			return null;

		Direction direction;
		visitedCells[position.y][position.x] = true; 
		maze[position.y][position.x]= '+'; 
		pathHistory.push(position);
		boolean hasMoved;

		while(!pathHistory.isEmpty()) {
			direction = getValidRandomDirection(position);
			hasMoved = false;

			if(direction == null)
				hasMoved = false;
			else {
				switch(direction){
				case UP:
					maze[position.y][position.x] = ' ';
					maze[position.y - 1][position.x] = ' ';
					maze[position.y - 2][position.x] = '+';
					visitedCells[position.y - 2][position.x] = true;
					hasMoved = true;
					position = new Point(position.x, position.y - 2);
					break;
				case DOWN:
					maze[position.y][position.x] = ' ';
					maze[position.y + 1][position.x] = ' ';
					maze[position.y + 2][position.x] = '+';
					visitedCells[position.y + 2][position.x] = true;
					hasMoved = true;				
					position = new Point(position.x, position.y + 2);
					break;
				case RIGHT:
					maze[position.y][position.x] = ' ';
					maze[position.y][position.x + 1] = ' ';
					maze[position.y][position.x + 2] = '+';
					visitedCells[position.y][position.x + 2] = true;
					hasMoved = true;		
					position = new Point(position.x + 2, position.y);
					break;
				case LEFT:
					maze[position.y][position.x] = ' ';
					maze[position.y][position.x - 1] = ' ';
					maze[position.y][position.x - 2] = '+';
					visitedCells[position.y][position.x - 2] = true;
					hasMoved = true;	
					position = new Point(position.x - 2, position.y);
					break;
				default:
					break;
				}
			}
			
			if(hasMoved)
				pathHistory.push(position);
			else
				position = pathHistory.pop();
		}

		mazeCleanup();
		placeHero();
		placeDragons();
		
		return maze;
	}

	private void placeDragons() {
				
	}

	private void placeHero() {
		
	}

	private void mazeCleanup() {
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				if(maze[j][i] == '+')
					maze[j][i] = ' ';
			}
		}
	}
}
