package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;

public class MazeGraphics extends JPanel {
	public static final int TEXTURE_SIZE = 643;

	//Images path
	private static final String WALL_PATH = "res/wall.png";
	private static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	private static final String HERO_ARMED_PATH = "res/hero_armed.png";
	private static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	private static final String DRAGON_SLEEPING_PATH = "res/dragon_sleeping.png";
	private static final String SWORD_PATH = "res/sword.png";

	//Images 
	private BufferedImage wall = null;		
	private BufferedImage heroUnarmed = null;
	private BufferedImage heroArmed = null;
	private BufferedImage dragonAwaken = null;
	private BufferedImage dragonSleeping = null;
	private BufferedImage sword = null;
	//private BufferedImage deadDragon = null; Maybe?

	private Maze maze;


	public MazeGraphics() {
		this.maze = null;

		try {
			wall = ImageIO.read(new File(WALL_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			heroUnarmed = ImageIO.read(new File(HERO_UNARMED_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			heroArmed = ImageIO.read(new File(HERO_ARMED_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dragonAwaken = ImageIO.read(new File(DRAGON_AWAKEN_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dragonSleeping = ImageIO.read(new File(DRAGON_SLEEPING_PATH));
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
					if(wall != null)
						g.drawImage(wall, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'E':
					if(sword != null) 
						g.drawImage(sword, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);						
					break;
				case 'H':
					if(heroUnarmed != null)
						g.drawImage(heroUnarmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'A':
					if(heroArmed != null)
						g.drawImage(heroArmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'D':
					if(dragonAwaken != null)
						g.drawImage(dragonAwaken, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'd':
					if(dragonSleeping != null)
						g.drawImage(dragonSleeping, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				default:
					break;
				}	
			}
		}
	}
}
