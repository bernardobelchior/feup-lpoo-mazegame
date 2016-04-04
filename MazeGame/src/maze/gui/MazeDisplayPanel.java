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
		MazeGraphics.drawMaze(g, parent.getMaze().getMazeArray(), null);
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
