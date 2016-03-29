package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;

public class MazeGraphics extends JPanel {
	public static final int TEXTURE_SIZE = 32;

	private static final String BUSH_PATH = "res/bush.png";
	private static final String HERO_PATH = "res/hero.png";
	private static final String DRAGON_PATH = "res/dragon.png";
	private static final String ARMED_HERO_PATH = "res/armed_hero.png";
	private static final String SWORD_PATH = "res/sword.png";


	private BufferedImage bush = null;		
	private BufferedImage hero = null;
	private BufferedImage dragon = null;
	private BufferedImage armedHero = null;
	private BufferedImage sword = null;
	//private BufferedImage deadDragon = null; Maybe?

	private Maze maze;


	public MazeGraphics() {
		this.maze = null;

		try {
			bush = ImageIO.read(new File(BUSH_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			hero = ImageIO.read(new File(HERO_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dragon = ImageIO.read(new File(DRAGON_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			armedHero = ImageIO.read(new File(ARMED_HERO_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			sword = ImageIO.read(new File(SWORD_PATH));
		} catch (Exception e) {
			e.printStackTrace();
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
		if(maze == null)
			return;

		char[][] mazeArray = maze.getMazeArray();

		for(int x = 0; x < mazeArray.length; x++){
			for(int y = 0; y < mazeArray[x].length; y++) {
				switch (mazeArray[y][x]) {
				case 'X':
					if(bush != null)
						g.drawImage(bush, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'E':
					if(sword != null) 
						g.drawImage(sword, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);						
				case 'H':
					if(hero != null)
						g.drawImage(hero, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
				default:
					break;
				}	
			}
		}
	}
}
