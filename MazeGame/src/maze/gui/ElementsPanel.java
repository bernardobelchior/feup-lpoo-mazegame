package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import maze.logic.Game.EntityType;

@SuppressWarnings("serial")
public class ElementsPanel extends JPanel{
	private ArrayList<JButton> elements;
	private static final int BUTTON_SIZE = MazeGraphics.TEXTURE_SIZE/2;

	public ElementsPanel(ManualMazeGeneratorWindow parent) {
		super();

		elements = new ArrayList<JButton>();

		//TODO: Create a derivative of JButton that handles this code better
		for(int i = 0; i < 8; i++) {
			JButton button = new JButton();
			button.setEnabled(true);
			button.setVisible(true);
			button.setBounds(0, i*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
			elements.add(button);
			this.add(button);
		}
		if(MazeGraphics.floor != null)
			elements.get(0).setIcon(new ImageIcon(MazeGraphics.floor));
		elements.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.BLANK);				
			}
		});
		
			if(MazeGraphics.wall != null)
				elements.get(1).setIcon(new ImageIcon(MazeGraphics.wall));
		elements.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.WALL);				
			}
		});
		if(MazeGraphics.heroUnarmed != null)
			elements.get(2).setIcon(new ImageIcon(MazeGraphics.heroUnarmed));
		elements.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_UNARMED);				
			}
		});
		if(MazeGraphics.heroArmed != null)
			elements.get(3).setIcon(new ImageIcon(MazeGraphics.heroArmed));
		elements.get(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_ARMED);				
			}
		});
		if(MazeGraphics.dragonAwaken != null)
			elements.get(4).setIcon(new ImageIcon(MazeGraphics.dragonAwaken));
		elements.get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_AWAKEN);				
			}
		});
		if(MazeGraphics.dragonSleeping != null)
			elements.get(5).setIcon(new ImageIcon(MazeGraphics.dragonSleeping));
		elements.get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_SLEEPING);				
			}
		});
		if(MazeGraphics.sword != null)
			elements.get(6).setIcon(new ImageIcon(MazeGraphics.sword));
		elements.get(6).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.SWORD);				
			}
		});
		if(MazeGraphics.exit != null)
			elements.get(7).setIcon(new ImageIcon(MazeGraphics.exit));
		elements.get(7).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.EXIT);				
			}
		});
	}

	public int getElementNumber() {
		return elements.size();
	}
}
