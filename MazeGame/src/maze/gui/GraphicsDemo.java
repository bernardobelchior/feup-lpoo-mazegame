package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;

public class GraphicsDemo extends JPanel {
	private BufferedImage bush;		
	private Maze maze;
	
	public GraphicsDemo() {
		this.maze = null;
		
		try {
			//FIXME: Add a relative path.
			bush = ImageIO.read(new File("/home/Bernardo/git/LPOO-MazeGame/MazeGame/res/bush.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
	}
	
	private void drawMaze(Graphics g) {
		//g.drawImage(bush, 0*bush.getWidth(), 0*bush.getHeight(), null);
		
		if(maze == null)
			return;
		
		char[][] mazeArray = maze.getMazeArray();
		
		for(int x = 0; x < mazeArray.length; x++){
			for(int y = 0; y < mazeArray[x].length; y++) {
				if(mazeArray[y][x] == 'X')
					g.drawImage(bush, x*bush.getWidth(), y*bush.getHeight(), null);
			}
		}
	}
}
