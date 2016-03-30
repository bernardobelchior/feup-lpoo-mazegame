package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import maze.logic.Maze;
import maze.logic.Game.Direction;

@SuppressWarnings("serial")
public class MazeDisplayPanel extends JPanel implements KeyListener {
	private Maze maze;


	public MazeDisplayPanel() {
		this.maze = null;
		addKeyListener(this);
		MazeGraphics.loadImages();
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
	}

	private void drawMaze(Graphics g) {		
		if(maze == null)
			return;

		char[][] mazeArray = maze.getMazeArray();

		for(int x = 0; x < mazeArray.length; x++){
			for(int y = 0; y < mazeArray[x].length; y++) {
				switch (mazeArray[y][x]) {
				case 'X':
					if(MazeGraphics.wall != null)
						g.drawImage(MazeGraphics.wall, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'E':
					if(MazeGraphics.sword != null) 
						g.drawImage(MazeGraphics.sword, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);						
					break;
				case 'H':
					if(MazeGraphics.heroUnarmed != null)
						g.drawImage(MazeGraphics.heroUnarmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'A':
					if(MazeGraphics.heroArmed != null)
						g.drawImage(MazeGraphics.heroArmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'D':
					if(MazeGraphics.dragonAwaken != null)
						g.drawImage(MazeGraphics.dragonAwaken, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'd':
					if(MazeGraphics.dragonSleeping != null)
						g.drawImage(MazeGraphics.dragonSleeping, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				default:
					break;
				}	
			}
		}
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			MazeGUI.mazeWindow.nextTurn(Direction.UP); 
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:	
			MazeGUI.mazeWindow.nextTurn(Direction.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			MazeGUI.mazeWindow.nextTurn(Direction.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			MazeGUI.mazeWindow.nextTurn(Direction.LEFT);
			break;
		}

	}


	public void keyReleased(KeyEvent arg0) {


	}


	public void keyTyped(KeyEvent arg0) {


	}
}
