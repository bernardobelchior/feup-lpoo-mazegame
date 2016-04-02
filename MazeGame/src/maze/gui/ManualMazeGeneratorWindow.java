package maze.gui;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.cli.GameInterface;
import maze.gui.MazeGraphics.DisplayMode;
import maze.logic.Game.EntityType;
import maze.logic.Game.GameMode;
import maze.logic.Maze;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ManualMazeGeneratorWindow extends JFrame {

	//TODO check if the maze created by the player is valid

	private JFrame ManualMazeGameWindow;
	private MazeDesignPanel mazePanel;
	private ElementsPanel elementsPanel;

	private EntityType selectedEntity;
	private DisplayMode displayMode;
	private GameMode gameMode;

	public ManualMazeGeneratorWindow(DisplayMode displayMode, GameMode gameMode) {
		MazeGraphics.loadImages();
		this.displayMode = displayMode;
		this.gameMode = gameMode;
		this.selectedEntity = null;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ManualMazeGameWindow = new JFrame();
		ManualMazeGameWindow.setTitle("Manual Maze Play Window");
		ManualMazeGameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ManualMazeGameWindow.setBounds(100, 100, 412, 305);
		ManualMazeGameWindow.setLayout(new BorderLayout());
		ManualMazeGameWindow.getContentPane().setLayout(new BorderLayout());
		ManualMazeGameWindow.setEnabled(true);
		ManualMazeGameWindow.setVisible(true);

		mazePanel = new MazeDesignPanel(this);
		mazePanel.setBounds(100, 11, 343, 285);
		ManualMazeGameWindow.getContentPane().add(mazePanel, BorderLayout.CENTER);

		JPanel mazeInfoPanel = new JPanel();
		mazeInfoPanel.setLayout(new GridBagLayout());
		ManualMazeGameWindow.getContentPane().add(mazeInfoPanel, BorderLayout.PAGE_START);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 1;
		gbc.weighty = 1;

		JLabel mazeDimensionLabel = new JLabel("Dimension:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		mazeInfoPanel.add(mazeDimensionLabel, gbc);

		SpinnerNumberModel model = new SpinnerNumberModel(11, 5, 50, 1);
		JSpinner mazeDimensionSpinner = new JSpinner(model);
		gbc.gridx = 1;
		gbc.gridy = 0;
		mazeInfoPanel.add(mazeDimensionSpinner, gbc);

		JButton createMazeButton = new JButton("Generate New Maze");
		createMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mazePanel.generateMaze(((Integer) mazeDimensionSpinner.getValue()).intValue());
				mazePanel.repaint();

				mazePanel.setBounds(mazePanel.getX(), mazePanel.getY(),
						((MazeDesignPanel) mazePanel).getMazeSize()*MazeGraphics.TEXTURE_SIZE,
						((MazeDesignPanel) mazePanel).getMazeSize()*MazeGraphics.TEXTURE_SIZE);
				setBounds(0, 0, 
						mazePanel.getX() + mazePanel.getWidth() + 30,
						Math.max(mazePanel.getY() + mazePanel.getHeight() + 50, elementsPanel.getHeight()));
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		mazeInfoPanel.add(createMazeButton, gbc);

		JButton launchGameButton = new JButton("Launch Game");
		launchGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isMazeValid(mazePanel.getMaze())) {
					JOptionPane.showMessageDialog(ManualMazeGameWindow, "Your maze is invalid.\nPlease make sure you generate a valid one.");
					return;
				}

				Maze maze = new Maze(mazePanel.getMaze(), gameMode);
				switch (displayMode) {
				case CONSOLE:
					new GameInterface(maze);
					break;
				case GRAPHICAL:
					new GraphicalGameWindow(maze);
					break;
				case TEXTUAL:
					new TextualGameWindow(maze);
					break;
				}
			}
		});
		gbc.gridy = 2;
		mazeInfoPanel.add(launchGameButton, gbc);

		gbc.gridy = 3;
		gbc.gridwidth = 5;
		elementsPanel = new ElementsPanel(this);
		mazeInfoPanel.add(elementsPanel, gbc);
	}

	public void setSelectedEntity(EntityType entityType) {
		this.selectedEntity = entityType;
	}

	public EntityType getSelectedEntity() {
		return selectedEntity;
	}

	private boolean isMazeValid(char[][] maze) {
		boolean hasExit = false;
		boolean hasHero = false;
		boolean isHeroArmed = false;
		int aliveDragonNumber = 0;
		int swordNumber = 0;

		for(int x = 0; x < maze.length; x++) {
			for (int y = 0; y < maze[x].length; y++) {
				switch(maze[y][x]) {
				case 'D':
				case 'd':
					aliveDragonNumber++;
					break;
				case 'S':
					if(isExitValid(x, y))
						hasExit = true;
					break;
				case 'E':
					swordNumber++;
					break;
				case 'H':
					hasHero = true;
					break;
				case 'A':
					hasHero = true;
					isHeroArmed = true;
					break;
				}
			}
		}
		
		return (hasHero && hasExit && ((aliveDragonNumber > 0 && (swordNumber > 0 || isHeroArmed)) || aliveDragonNumber == 0));
	}
	
	//TODO check if the exit is valid
	private boolean isExitValid(int x, int y) {
		return true;
	}
}
