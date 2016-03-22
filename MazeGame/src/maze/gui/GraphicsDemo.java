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
	
	public GraphicsDemo(Maze maze) {
		this.maze = maze;
		
		try {
			//FIXME: Add a relative path.
			bush = ImageIO.read(new File("/home/Bernardo/git/LPOO-MazeGame/MazeGame/res/bush.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bush, 0, 0, null);
	}
	
	public void update() {
		//for(int i = 0; i < )
	}
}
