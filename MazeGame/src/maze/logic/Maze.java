package maze.logic;

import java.util.Random;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Maze {
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private Exit exit;

	private char maze[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	public Maze() {
		hero = new Hero(1, 1);
		setChar(hero.getX(), hero.getY(), hero.getChar());

		dragon = new Dragon(1, 3);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());

		sword = new Sword(1, 8);
		setChar(sword.getX(), sword.getY(), sword.getChar());

		exit = new Exit(9, 5);
		setChar(exit.getX(), exit.getY(), exit.getChar());
	}

	private void setChar(int x, int y, char c) {
		maze[y][x] = c;
	}

	private void unsetChar(int x, int y) {
		maze[y][x] = ' ';
	}

	private char getChar(int x, int y) {
		return maze[y][x];
	}

	public void moveHero(Game.Direction direction) {
		if (canMoveHero(hero.getX(), hero.getY(), direction)) {
			unsetChar(hero.getX(), hero.getY());
			hero.move(direction);
			if (!sword.getPickedUp() && sword.getX() == hero.getX() && sword.getY() == hero.getY()) {
				pickUpSword();
			}
			setChar(hero.getX(), hero.getY(), hero.getChar());
		} else
			System.out.println("You cannot move in this direction");
		moveDragon(direction);	//acrescentei isto para o dragao se mover de cada vez que o heroi se move
	}

	public Game.Direction RandomDirectionDragon(){
		Game.Direction direction;
		String direction_string;
		int random_direction;
		Random rn = new Random();
		int n = 4 ;
		int i = rn.nextInt() % n;
		random_direction =  1 + i;
		switch (random_direction){
		case 1:
			direction=Game.Direction.UP;
			break;
		case 2:
			direction=Game.Direction.DOWN;
			break;
		case 3:
			direction=Game.Direction.RIGHT;
			break;
		case 4:
			direction=Game.Direction.LEFT;
			break;

		}

		return direction;	//TODO isto deve estar tudo mal resolve este erro pf e depois explica me
	}

	public void moveDragon(Game.Direction direction) {
		Game.Direction RandomDirectionDragon();
		while (!canMoveDragon(dragon.getX(), dragon.getY(), direction)) {
			Game.Direction RandomDirectionDragon();
		}
		unsetChar(dragon.getX(), dragon.getY());
		dragon.move(direction);
		setChar(dragon.getX(), dragon.getY(), dragon.getChar());
		//TODO if's no caso de encontrar a espada mudar char para F e noo caso de ficar ao lado do heroi morrer ou matar
		//eu faço isto so nao tive tempo faço amanha!!!!!!!!!!!!
	}

	private boolean canMoveHero(int x, int y, Game.Direction direction) {

		switch (direction) {

		case UP:
			if (getChar(x, y - 1) == 'X' || (getChar(x, y - 1) == 'S' && dragon.isAlive()))
				return false;
			break;
		case DOWN:
			if (getChar(x, y + 1) == 'X' || (getChar(x, y + 1) == 'S' && dragon.isAlive()))
				return false;
			break;
		case RIGHT:
			if (getChar(x + 1, y) == 'X' || (getChar(x + 1, y) == 'S' && dragon.isAlive()))
				return false;
			break;
		case LEFT:
			if (getChar(x - 1, y) == 'X' || (getChar(x - 1, y) == 'S' && dragon.isAlive()))
				return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}


	private boolean canMoveDragon(int x, int y, Game.Direction direction) {

		switch (direction) {

		case UP:
			if (getChar(x, y - 1) == 'X' || (getChar(x, y - 1) == 'S'))
				return false;
			break;
		case DOWN:
			if (getChar(x, y + 1) == 'X' || (getChar(x, y + 1) == 'S' ))
				return false;
			break;
		case RIGHT:
			if (getChar(x + 1, y) == 'X' || (getChar(x + 1, y) == 'S' ))
				return false;
			break;
		case LEFT:
			if (getChar(x - 1, y) == 'X' || (getChar(x - 1, y) == 'S' ))
				return false;
			break;
		case STAY:
			return true;
		}
		return true; 
	}


	public void update() {
		if (isHeroNextToDragon() && hero.getSwordEquipped()) {
			dragon.kill();
			unsetChar(dragon.getX(), dragon.getY());
		}
	}

	private void pickUpSword() {
		hero.equipSword();
		sword.pickUp();
		unsetChar(sword.getX(), sword.getY());
	}

	private boolean isHeroNextToDragon() {
		if(dragon.isAlive()){
			// Checks if the hero is in an adjacent square to the dragon
			if ((Math.abs(hero.getX() - dragon.getX()) == 1 && Math.abs(hero.getY() - dragon.getY()) == 0)
					|| (Math.abs(hero.getX() - dragon.getX()) == 0 && Math.abs(hero.getY() - dragon.getY()) == 1)) {
				return true;
			}
		}
		return false;
	}

	private boolean isHeroOnExit() {
		if (hero.getX() == exit.getX() && hero.getY() == exit.getY()) {
			return true;
		}
		return false;
	}

	public Entity checkGameState() {
		if (isHeroOnExit() && !dragon.isAlive()) {
			return hero;
		}

		if (isHeroNextToDragon() && !hero.getSwordEquipped()) {
			return dragon;
		}

		return null;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				result += maze[i][j];
			}
			result += '\n';
		}

		return result;
	}

}
