package maze.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MazeGraphics {
	public enum DisplayMode { CONSOLE, GRAPHICAL, TEXTUAL };
	public static final int TEXTURE_SIZE = 64;

	//Images path
	private static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	private static final String DRAGON_SLEEPING_PATH = "res/dragon_asleep.png";
	private static final String EXIT_PATH = "res/exit.jpg";
	private static final String NO_EXIT = "res/no_exit.jpg";
	private static final String FRAME_PATH = "res/frame.png";
	private static final String FLOOR_PATH = "res/floor.jpg";
	private static final String GAME_RUNNING_PATH = "res/wall.jpg";
	private static final String HERO_ARMED_PATH = "res/hero_armed.png";
	private static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	private static final String SWORD_PATH = "res/sword.png";
	private static final String WALL_PATH = "res/wall.png";

	//Images 	
	public static BufferedImage dragonAwaken = null;
	public static BufferedImage dragonSleeping = null;
	public static BufferedImage exit = null;
	public static BufferedImage noExit = null;
	public static BufferedImage frame = null;
	public static BufferedImage floor = null;
	public static BufferedImage gameRunning = null;
	public static BufferedImage heroArmed = null;
	public static BufferedImage heroUnarmed = null;
	public static BufferedImage sword = null;
	public static BufferedImage wall = null;

	public static void loadImages() {
		//If the images are null, load them again. Otherwise, do nothing.		
		dragonAwaken = (dragonAwaken == null) ? loadImage(DRAGON_AWAKEN_PATH) : dragonAwaken;
		dragonSleeping = (dragonSleeping == null) ? loadImage(DRAGON_SLEEPING_PATH) : dragonSleeping;
		exit = (exit == null) ? loadImage(EXIT_PATH) : exit;
		noExit = (noExit == null) ? loadImage (NO_EXIT) : noExit;
		frame = (frame == null) ? loadImage(FRAME_PATH) : frame;
		floor = (floor == null) ? loadImage(FLOOR_PATH) : floor;
		gameRunning = (gameRunning == null) ? loadImage(GAME_RUNNING_PATH) : gameRunning;
		heroArmed = (heroArmed == null) ? loadImage(HERO_ARMED_PATH) : heroArmed;
		heroUnarmed = (heroUnarmed == null) ? loadImage(HERO_UNARMED_PATH) : heroUnarmed;
		sword = (sword == null) ? loadImage(SWORD_PATH) : sword;
		wall = (wall == null) ? loadImage(WALL_PATH) : wall;
	}


	private static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return image;
	}

	public static void deleteImages() {
		dragonAwaken = null;
		dragonSleeping = null;
		exit = null;
		frame = null;
		gameRunning = null;
		heroArmed = null;
		heroUnarmed = null;
		sword = null;
		wall = null;		
	}

	public static boolean drawImageOnGridPosition(Graphics g, BufferedImage image, int x, int y) {
		if(image == null) 
			return false;

		g.drawImage(image, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
		return true;
	}

	public static void centerFrame(JFrame frame) {
		//Gets the screenSize to center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth())/2,
				(screenSize.height - frame.getHeight())/2);
	}
}
