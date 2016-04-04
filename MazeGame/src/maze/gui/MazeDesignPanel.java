package maze.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Game.EntityType;

@SuppressWarnings("serial")
public class MazeDesignPanel extends JPanel implements MouseListener {
	private char[][] maze;
	private int size;
	private boolean heroPlaced;
	private boolean exitPlaced;
	private ManualMazeGeneratorWindow parent;

	public MazeDesignPanel(ManualMazeGeneratorWindow parent) {
		this.maze = null;
		this.parent = parent;
		this.size = 11;
		generateMaze();
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
		exitPlaced = false;

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
		MazeGraphics.drawMaze(g, maze, MazeGraphics.frame);
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

		int x = e.getX()/MazeGraphics.TEXTURE_SIZE;
		int y = e.getY()/MazeGraphics.TEXTURE_SIZE;

		if(e.getButton() == MouseEvent.BUTTON3) {
			//Right mouse button
			deleteEntityAt(x, y);
		} else if (e.getButton() == MouseEvent.BUTTON1){
			//Left mouse button
			if(parent.getSelectedEntity() == EntityType.EXIT || deleteEntityAt(x, y))
				createEntityAt(x, y, parent.getSelectedEntity());
		}

		repaint();
	}

	private boolean isValidPositionForExit(int x, int y) {
		return !((x == 0 && y == 0) || (x == 0 && y == size - 1) ||
				(x == size - 1 && y == 0) || (x == size - 1 && y == size - 1));
	}

	public int getMazeSize() {
		return maze.length;
	}

	public char[][] getMaze() {
		return maze;
	}

	private boolean deleteEntityAt(int x, int y) {
		switch (maze[y][x]) {
		case 'X':
			if(x == 0 || x == size - 1 || y == 0 || y == size - 1) {
				JOptionPane.showMessageDialog(parent, "You cannot delete a bounding wall.");
				return false;
			}
			break;
		case 'S':
			exitPlaced = false;
			maze[y][x] = 'X';
			return true;
		case 'H':
		case 'A':
			heroPlaced = false;
			break;
		}

		maze[y][x] = ' ';
		return true;
	}

	private void createEntityAt(int x, int y, EntityType entity) {
		switch (entity) {
		case HERO_ARMED:
			if(heroPlaced) {
				JOptionPane.showMessageDialog(parent, "An hero has already been placed.");
				return;
			}
			heroPlaced = true;
			maze[y][x] = 'A';
			break;
		case HERO_UNARMED:
			if(heroPlaced) {
				JOptionPane.showMessageDialog(parent, "An hero has already been placed.");
				return;
			}
			heroPlaced = true;
			maze[y][x] = 'H';
			break;
		case EXIT:
			if(exitPlaced) {
				JOptionPane.showMessageDialog(parent, "There can only be on exit in the maze.");
				return;
			}
			if(isValidPositionForExit(x, y)) {
				exitPlaced = true;
				maze[y][x] = 'S';	
			} else
				JOptionPane.showMessageDialog(parent, "Invalid exit location.");
			break;
		case DRAGON_AWAKEN:
			maze[y][x] = 'D';
			break;
		case DRAGON_SLEEPING:
			maze[y][x] = 'd';
			break;
		case SWORD:
			maze[y][x] = 'E';
			break;
		default: 
			break;
		}
	}
}
