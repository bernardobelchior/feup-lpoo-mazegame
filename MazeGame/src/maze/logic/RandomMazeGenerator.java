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
	int dragonNumber;

	public RandomMazeGenerator(int size, int dragonNumber){
		this.size = size;
		this.dragonNumber = dragonNumber;
		if(this.size % 2 == 0)
			this.size++;
	}

	public void initializeVariables(){
		visitedCells = new boolean[size][size];
		maze = new char[size][size];
		pathHistory = new Stack<Point>();
		
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

	public void generateExit(){
		Random random = new Random();
		int x,y;

		do {
			x = random.nextInt(size);
			y = random.nextInt(size);
		} while(!(((x == 0 || x == size - 1) && y % 2 == 1) || ((y == 0 || y == size - 1) && x % 2 == 1)));

		exit = new Point(x, y);
		maze[exit.y][exit.x] = 'S';
	}

	public Point getValidStartingPosition() {
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

		while(!pathHistory.isEmpty()) {
			direction = getValidRandomDirection(position);

			if(direction == null)
				position = pathHistory.pop();
			else {
				switch(direction){
				case UP:
					maze[position.y][position.x] = ' ';
					maze[position.y - 1][position.x] = ' ';
					maze[position.y - 2][position.x] = '+';
					visitedCells[position.y - 2][position.x] = true;
					position = new Point(position.x, position.y - 2);
					break;
				case DOWN:
					maze[position.y][position.x] = ' ';
					maze[position.y + 1][position.x] = ' ';
					maze[position.y + 2][position.x] = '+';
					visitedCells[position.y + 2][position.x] = true;
					position = new Point(position.x, position.y + 2);
					break;
				case RIGHT:
					maze[position.y][position.x] = ' ';
					maze[position.y][position.x + 1] = ' ';
					maze[position.y][position.x + 2] = '+';
					visitedCells[position.y][position.x + 2] = true;
					position = new Point(position.x + 2, position.y);
					break;
				case LEFT:
					maze[position.y][position.x] = ' ';
					maze[position.y][position.x - 1] = ' ';
					maze[position.y][position.x - 2] = '+';
					visitedCells[position.y][position.x - 2] = true;	
					position = new Point(position.x - 2, position.y);
					break;
				default:
					break;
				}
				pathHistory.push(position);
			}
		}

		placeHero();
		placeDragons();
		placeSword();
		mazeCleanup();
		
		return maze;
	}

	private void placeSword() {
		Random random = new Random();
		int x, y;
		
		do {
			x = random.nextInt(size);
			y = random.nextInt(size);
		} while(maze[y][x] != ' ');
		
		maze[y][x] = 'E';
	}

	private void placeDragons() {
		Random random = new Random();
		int i, x, y;
		i = 0;
		//TODO Make a better check to see if the dragons dont end up adjacent to a hero
		//or the path to the sword is inaccessible
		while(i < dragonNumber) {
			x = random.nextInt(size);
			y = random.nextInt(size);
			if(!(maze[y][x] == 'X' || maze[y][x] == 'S' || maze[y][x] == 'H')) {
				i++;
				maze[y][x] = 'D';
			}
		}
		
		//May be useful in the future to ensure dragon don't leave
		//the hero in an accessible path to the sword
		/*for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				if(maze[j][i] == '+')
					maze[j][i] = 'D';
			}
		}*/
	}

	private void placeHero() {
		Random random = new Random();
		int x, y;
		Point position;
		
		//Adds a hero in a place where is distance
		//between its position and the exit is at least
		//one third of the maze's size
		do {
			x = random.nextInt(size);
			y = random.nextInt(size);
			position = new Point(x, y);
		} while(position.distance(exit) < size/3 || maze[y][x] != ' ');
		
		maze[y][x] = 'H';
	}

	private void mazeCleanup() {
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				if(maze[j][i] == '+')
					maze[j][i] = ' ';
			}
		}
	}
	
	public Point getExit(){
		return exit;
	}
}
