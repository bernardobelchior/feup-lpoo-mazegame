package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MazeGraphics {
	public static final int TEXTURE_SIZE = 64;

	//Images path
	public static final String WALL_PATH = "res/wall.png";
	public static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	public static final String HERO_ARMED_PATH = "res/hero_armed.png";
	public static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	public static final String DRAGON_SLEEPING_PATH = "res/dragon_sleeping.png";
	public static final String SWORD_PATH = "res/sword.png";
	public static final String FRAME_PATH = "res/frame.png";
	public static final String EXIT_PATH = "res/exit.png";

	//Images 
	public static BufferedImage wall = null;		
	public static BufferedImage heroUnarmed = null;
	public static BufferedImage heroArmed = null;
	public static BufferedImage dragonAwaken = null;
	public static BufferedImage dragonSleeping = null;
	public static BufferedImage sword = null;
	public static BufferedImage exit = null;
	public static BufferedImage frame = null;
	//private BufferedImage deadDragon = null; Maybe?

	public static void loadImages() {
		loadImage(dragonAwaken, DRAGON_AWAKEN_PATH);
		//loadImage(dragonSleeping, DRAGON_SLEEPING_PATH);
		//loadImage(exit, EXIT_PATH);
		loadImage(frame, FRAME_PATH);
		loadImage(heroArmed, HERO_ARMED_PATH);
		loadImage(heroUnarmed, HERO_UNARMED_PATH);
		loadImage(sword, SWORD_PATH);
		loadImage(wall, WALL_PATH);
	}
	

	private static void loadImage(BufferedImage image, String path) {
		if(image == null) {
			try {
				image = ImageIO.read(new File(path));
			} catch (Exception e) {
				e.printStackTrace();
				image = null;
			}
		}
	}
	
	public static void deleteImages() {
		wall = null;		
		heroUnarmed = null;
		heroArmed = null;
		dragonAwaken = null;
		dragonSleeping = null;
		sword = null;
		exit = null;
		frame = null;
	}

}
