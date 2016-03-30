package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import maze.logic.Game.EntityType;

@SuppressWarnings("serial")
public class MazeElementsPanel extends JPanel{


	//FIXME: Duplicated code. Find a way to resolve the issue.
	//Images path
	private static final String WALL_PATH = "res/wall.png";
	private static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	private static final String HERO_ARMED_PATH = "res/hero_armed.png";
	private static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	private static final String DRAGON_SLEEPING_PATH = "res/dragon_sleeping.png";
	private static final String SWORD_PATH = "res/sword.png";
	private static final String FRAME_PATH = "res/frame.png";
	
	private ArrayList<JButton> elements;
	private ManualMazeGeneratorWindow parent;
	
	public MazeElementsPanel(ManualMazeGeneratorWindow parent) {
		super();
		this.parent = parent;
		
		elements = new ArrayList<JButton>();
		
		for(int i = 0; i < 6; i++) {
			JButton button = new JButton();
			button.setEnabled(true);
			button.setVisible(true);
			button.setBounds(0, i*MazeGraphics.TEXTURE_SIZE, MazeGraphics.TEXTURE_SIZE, MazeGraphics.TEXTURE_SIZE);
			elements.add(button);
			this.add(button);
		}
		
		elements.get(0).setIcon(new ImageIcon(WALL_PATH));
		elements.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.WALL);				
			}
		});
		elements.get(1).setIcon(new ImageIcon(HERO_UNARMED_PATH));
		elements.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_UNARMED);				
			}
		});
		elements.get(2).setIcon(new ImageIcon(HERO_ARMED_PATH));
		elements.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.HERO_ARMED);				
			}
		});
		
		elements.get(3).setIcon(new ImageIcon(DRAGON_AWAKEN_PATH));
		elements.get(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_AWAKEN);				
			}
		});
		elements.get(4).setIcon(new ImageIcon(DRAGON_SLEEPING_PATH));
		elements.get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.DRAGON_SLEEPING);				
			}
		});
		elements.get(5).setIcon(new ImageIcon(SWORD_PATH));
		elements.get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setSelectedEntity(EntityType.SWORD);				
			}
		});
	}
	
	public int getElementNumber() {
		return elements.size();
	}
}
