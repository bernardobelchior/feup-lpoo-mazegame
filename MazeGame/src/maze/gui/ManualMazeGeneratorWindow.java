package maze.gui;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import maze.cli.GameInterface;
import maze.gui.MazeGraphics.DisplayMode;
import maze.logic.Game.EntityType;
import maze.logic.Game.GameMode;
import maze.logic.Maze;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ManualMazeGeneratorWindow extends JFrame implements WindowListener {

	private JFrame manualMazeGeneratorFrame;
	private MazeDesignPanel mazeDesignPanel;
	private ElementsPanel elementsPanel;
	private JFrame parent;
	private JScrollPane mazeDesignScrollPane;
	
	private EntityType selectedEntity;
	private DisplayMode displayMode;
	private GameMode gameMode;
	private boolean launchedWindow;

	public ManualMazeGeneratorWindow(JFrame parent, DisplayMode displayMode, GameMode gameMode) {
		MazeGraphics.loadImages();
		this.parent = parent;
		this.displayMode = displayMode;
		this.gameMode = gameMode;
		this.selectedEntity = null;
		this.launchedWindow = false;
		initialize();
		manualMazeGeneratorFrame.addWindowListener(this);
		adjustWindowToScreen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		manualMazeGeneratorFrame = new JFrame();
		manualMazeGeneratorFrame.setTitle("Manual Maze Play Window");
		manualMazeGeneratorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		manualMazeGeneratorFrame.setBounds(100, 100, 800, 600);
		manualMazeGeneratorFrame.setLayout(new BorderLayout());
		manualMazeGeneratorFrame.getContentPane().setLayout(new BorderLayout());
		manualMazeGeneratorFrame.setResizable(false);
		manualMazeGeneratorFrame.setEnabled(true);
		manualMazeGeneratorFrame.setVisible(true);

		mazeDesignPanel = new MazeDesignPanel(this);
		mazeDesignPanel.setPreferredSize(new Dimension(100, 100));
		
		mazeDesignScrollPane = new JScrollPane(mazeDesignPanel);
		mazeDesignScrollPane.setBounds(0, 0, 300, 300);
		manualMazeGeneratorFrame.getContentPane().add(mazeDesignScrollPane, BorderLayout.CENTER);
		
		JPanel mazeInfoPanel = new JPanel();
		mazeInfoPanel.setLayout(new GridBagLayout());
		manualMazeGeneratorFrame.getContentPane().add(mazeInfoPanel, BorderLayout.PAGE_START);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 1;
		gbc.weighty = 1;

		JPanel mazeDimensionPanel = new JPanel();
		mazeDimensionPanel.setLayout(new GridLayout(1, 2, 10, 0));
		gbc.gridx = 0;
		gbc.gridy = 0;
		mazeInfoPanel.add(mazeDimensionPanel, gbc);
		
		JLabel mazeDimensionLabel = new JLabel("Dimension:");
		mazeDimensionPanel.add(mazeDimensionLabel, gbc);

		SpinnerNumberModel model = new SpinnerNumberModel(11, 5, 50, 1);
		JSpinner mazeDimensionSpinner = new JSpinner(model);
		mazeDimensionPanel.add(mazeDimensionSpinner, gbc);

		JButton createMazeButton = new JButton("Generate New Maze");
		createMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mazeDesignPanel.generateMaze(((Integer) mazeDimensionSpinner.getValue()).intValue());
				mazeDesignPanel.repaint();

				adjustWindowToScreen();
			}
		});
		gbc.gridx = 1;
		mazeInfoPanel.add(createMazeButton, gbc);

		JButton launchGameButton = new JButton("Launch Game");
		launchGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isMazeValid(mazeDesignPanel.getMaze())) {
					JOptionPane.showMessageDialog(manualMazeGeneratorFrame, "Your maze is invalid.\nPlease make sure you generate a valid one.");
					return;
				}

				Maze maze = new Maze(mazeDesignPanel.getMaze(), gameMode);
				launchedWindow = true;
				switch (displayMode) {
				case CONSOLE:
					new GameInterface(parent, maze);
					break;
				case GRAPHICAL:
					new GraphicalGameWindow(parent, maze);
					break;
				case TEXTUAL:
					new TextualGameWindow(parent, maze);
					break;
				}
				manualMazeGeneratorFrame.dispose();
			}
		});
		gbc.gridx = 2;
		mazeInfoPanel.add(launchGameButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
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

		for(int y = 1; y < maze.length; y++) {
			if(maze[y][0] == 'S') {
				if(hasExit)
					return false;
				else
					hasExit = true;
			} else if(maze[y][0] != 'X')
				return false;
			
			if(maze[y][maze.length - 1] == 'S') {
				if(hasExit)
					return false;
				else
					hasExit = true;
			} else if(maze[y][maze.length - 1] != 'X')
				return false;
		}
		
		for(int x = 1; x < maze.length; x++) {
			if(maze[0][x] == 'S') {
				if(hasExit)
					return false;
				else
					hasExit = true;
			} else if(maze[0][x] != 'X')
				return false;
			
			if(maze[maze.length - 1][x] == 'S') {
				if(hasExit)
					return false;
				else
					hasExit = true;
			} else if(maze[maze.length - 1][x] != 'X')
				return false;
		}
		
		for(int x = 1; x < maze.length - 1; x++) {
			for (int y = 1; y < maze[x].length - 1; y++) {
				switch(maze[y][x]) {
				case 'D':
				case 'd':
					aliveDragonNumber++;
					break;
				case 'S':
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

	private void adjustWindowToScreen() {
		mazeDesignPanel.setPreferredSize(new Dimension(mazeDesignPanel.getMazeSize()*MazeGraphics.TEXTURE_SIZE, mazeDesignPanel.getMazeSize()*MazeGraphics.TEXTURE_SIZE));
		int width = Math.max(elementsPanel.getX() + elementsPanel.getPreferredSize().width + manualMazeGeneratorFrame.getInsets().left + manualMazeGeneratorFrame.getInsets().right,
				Math.min(
				manualMazeGeneratorFrame.getInsets().left + manualMazeGeneratorFrame.getInsets().right + mazeDesignScrollPane.getPreferredSize().width + mazeDesignScrollPane.getVerticalScrollBar().getSize().width,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width));
		int height = Math.min(
				manualMazeGeneratorFrame.getInsets().top + manualMazeGeneratorFrame.getInsets().bottom + mazeDesignScrollPane.getPreferredSize().height + mazeDesignScrollPane.getHorizontalScrollBar().getSize().height,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		manualMazeGeneratorFrame.setSize(width, height);
		MazeGraphics.centerFrame(manualMazeGeneratorFrame);
		manualMazeGeneratorFrame.setLocation(manualMazeGeneratorFrame.getLocation().x, 0);
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if(!launchedWindow)
			parent.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
