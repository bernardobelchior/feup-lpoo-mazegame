package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import maze.logic.Game.EntityType;

@SuppressWarnings("serial")
public class ElementsPanel extends JPanel{
	private ArrayList<JButton> elements;
	private static final int BUTTON_SIZE = MazeGraphics.TEXTURE_SIZE/2;
	private static final BufferedImage[] buttonImages = 
		{ MazeGraphics.wall, MazeGraphics.heroUnarmed, MazeGraphics.heroArmed, MazeGraphics.dragonAwaken, 
		  MazeGraphics.dragonSleeping, MazeGraphics.sword, MazeGraphics.exit };
	
	public ElementsPanel(ManualMazeGeneratorWindow parent) {
		super();

		elements = new ArrayList<JButton>();

		//TODO: Create a derivative of JButton that handles this code better
		for(int i = 0; i < 7; i++) {
			JButton button = new JButton();
			button.setEnabled(true);
			button.setVisible(true);
			button.setBounds(0, i*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
			if(buttonImages[i] != null)
				button.setIcon(new ImageIcon(buttonImages[i]));
			elements.add(button);
			this.add(button);
		}
		
		elements.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.WALL);				
			}
		});
	
		elements.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_UNARMED);				
			}
		});

		elements.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_ARMED);				
			}
		});
		
		elements.get(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_AWAKEN);				
			}
		});
		
		elements.get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_SLEEPING);				
			}
		});
		
		elements.get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.SWORD);				
			}
		});
		
		elements.get(6).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.EXIT);				
			}
		});
	}

	public int getElementNumber() {
		return elements.size();
	}
}
