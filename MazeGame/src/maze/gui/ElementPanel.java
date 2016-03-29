package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ElementPanel extends JPanel{


	//FIXME: Duplicated code. Find a way to resolve the issue.
	//Images path
	private static final String WALL_PATH = "res/wall.png";
	private static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	private static final String HERO_ARMED_PATH = "res/hero_armed.png";
	private static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	private static final String DRAGON_SLEEPING_PATH = "res/dragon_sleeping.png";
	private static final String SWORD_PATH = "res/sword.png";
	
	private ArrayList<JButton> elements;

	public ElementPanel() {
		super();
		
		elements = new ArrayList<JButton>();
		
		for(int i = 0; i < 6; i++) {
			elements.add(new JButton());
			//elements.get(i).
		}
		
	}
	
	
}
