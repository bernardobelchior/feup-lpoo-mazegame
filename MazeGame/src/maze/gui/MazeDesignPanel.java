package maze.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazeDesignPanel extends JPanel implements MouseListener {
	private char[][] maze;
	private int size;
	private boolean heroPlaced = false;
	private ManualMazeGeneratorWindow parent;

	public MazeDesignPanel(ManualMazeGeneratorWindow parent) {
		this.maze = null;
		this.parent = parent;
		this.size = 11;
		addMouseListener(this);
	}

	public void setSize(int size) {
		//Make sure the size is between 5 and 50.
		this.size = Math.min(50, Math.max(5, size));
	}

	public void generateMaze() {
		generateMaze(size);
	}

	public void generateMaze(int size) {
		setSize(size);
		maze = new char[size][size];
		heroPlaced = false;

		//Sets walls all around the maze.
		for(int y = 0; y < size; y++){
			maze[y][0] = 'X';
			maze[y][size-1] = 'X';
		}

		for(int x = 1; x < size - 1; x++){
			maze[0][x] = 'X';
			maze[size-1][x] = 'X';
		}

		//Sets the rest of the maze as blank spaces.
		for(int x = 1; x < size - 1; x++) {
			for(int y = 1; y < size - 1; y++) {
				maze[y][x] = ' ';
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
	}

	private void drawMaze(Graphics g) {
		if(maze == null)
			return;

		for(int x = 0; x < maze.length; x++) {
			for(int y = 0; y < maze[x].length; y++) {
				switch (maze[y][x]) {
				case 'X':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.wall, x, y);
					break;
				case 'A':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.heroArmed, x, y);
					break;
				case 'H':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.heroUnarmed, x, y);
					break;
				case 'D':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.dragonAwaken, x, y);
					break;
				case 'd':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.dragonSleeping, x, y);
					break;
				case 'E':
					MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.sword, x, y);
					break;
				default:
					break;
				}
				MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.frame, x, y);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) {
		if(maze == null || parent.getSelectedEntity() == null)
			return;

		switch (parent.getSelectedEntity()) {
		case BLANK:
			if(isHeroOnPosition(e.getX()/MazeGraphics.TEXTURE_SIZE, e.getY()/MazeGraphics.TEXTURE_SIZE))
				heroPlaced = false;
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = ' ';
			break;
		case WALL:
			if(isHeroOnPosition(e.getX()/MazeGraphics.TEXTURE_SIZE, e.getY()/MazeGraphics.TEXTURE_SIZE))
				heroPlaced = false;
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'X';
			break;
		case HERO_ARMED:
			if(!heroPlaced) {
				maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'A';
				heroPlaced = true;
			} else {
				JOptionPane.showMessageDialog(parent, "An hero has already been placed.");
			}
			break;
		case HERO_UNARMED:
			if(!heroPlaced) {
				maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'H';
				heroPlaced = true;
			} else {
				JOptionPane.showMessageDialog(parent, "An hero has already been placed.");
			}
			break;
		case DRAGON_AWAKEN:
			if(isHeroOnPosition(e.getX()/MazeGraphics.TEXTURE_SIZE, e.getY()/MazeGraphics.TEXTURE_SIZE))
				heroPlaced = false;
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'D';
			break;
		case DRAGON_SLEEPING:
			if(isHeroOnPosition(e.getX()/MazeGraphics.TEXTURE_SIZE, e.getY()/MazeGraphics.TEXTURE_SIZE))
				heroPlaced = false;
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'd';
			break;
		case SWORD: 
			if(isHeroOnPosition(e.getX()/MazeGraphics.TEXTURE_SIZE, e.getY()/MazeGraphics.TEXTURE_SIZE))
				heroPlaced = false;
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'E';
			break;
		default:
			break;
		}

		repaint();
	}

	public int getMazeSize() {
		return maze.length;
	}

	public char[][] getMaze() {
		return maze;
	}

	private boolean isHeroOnPosition(int x, int y) {
		return (maze[y][x] == 'A' || maze[y][x] == 'H');
	}
}
