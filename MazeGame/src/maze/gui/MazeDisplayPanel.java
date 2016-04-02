package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import maze.logic.Game.Direction;

@SuppressWarnings("serial")
public class MazeDisplayPanel extends JPanel implements KeyListener {
	private GraphicalGameWindow parent;

	public MazeDisplayPanel(GraphicalGameWindow parent) {
		this.parent = parent;
		addKeyListener(this);
		this.requestFocus();
		MazeGraphics.loadImages();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
	}

	private void drawMaze(Graphics g) {		
		if(parent.getMaze() == null)
			return;

		char[][] mazeArray = parent.getMaze().getMazeArray();

		for(int x = 0; x < mazeArray.length; x++){
			for(int y = 0; y < mazeArray[x].length; y++) {
				switch (mazeArray[y][x]) {
				case 'X':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.wall, x, y);
					break;
				case 'E':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.sword, x, y);						
					break;
				case 'H':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.heroUnarmed, x, y);
					break;
				case 'A':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.heroArmed, x, y);
					break;
				case 'D':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.dragonAwaken, x, y);
					break;
				case 'd':
						MazeGraphics.drawImageOnGridPosition(g, MazeGraphics.dragonSleeping, x, y);
					break;
				default:
					break;
				}	
			}
		}
	}

	public void keyPressed(KeyEvent keyEvent) {	}

	public void keyReleased(KeyEvent keyEvent) {	
		switch (keyEvent.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			parent.nextTurn(Direction.UP); 
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:	
			parent.nextTurn(Direction.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			parent.nextTurn(Direction.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			parent.nextTurn(Direction.LEFT);
			break;
		case KeyEvent.VK_ESCAPE:
			parent.close();
		}
	}

	public void keyTyped(KeyEvent keyEvent) {	}
}
