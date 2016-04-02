package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import maze.logic.Maze;
import maze.logic.Game.Direction;
import maze.logic.Game.GameState;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

public class GraphicalGameWindow {

	private JFrame graphicalGameFrame;
	private Maze maze;
	private JLabel instructionsLabel;
	//private JButton upButton, downButton, leftButton, rightButton;
	private MazeDisplayPanel mazeDisplayPanel;
	private GameStateDisplayPanel gameStatePanel = new GameStateDisplayPanel();

	/**
	 * Create the application.
	 */
	public GraphicalGameWindow(Maze maze) {
		initialize();
		this.maze = maze;
		prepareComponents();
		
		gameStatePanel.updateState(maze.getGameState());
		mazeDisplayPanel.requestFocusInWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		graphicalGameFrame = new JFrame();
		graphicalGameFrame.setTitle("Play Window");
		graphicalGameFrame.setBounds(100, 100, 622, 482);
		graphicalGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		graphicalGameFrame.getContentPane().setLayout(null);
		graphicalGameFrame.setEnabled(true);
		graphicalGameFrame.setResizable(false);
		graphicalGameFrame.setVisible(true);
				
		instructionsLabel = new JLabel("");
		instructionsLabel.setBounds(26, 169, 179, 14);
		graphicalGameFrame.getContentPane().add(instructionsLabel);
		
	/*	gameStatePanel = new GameStateDisplayPanel();
		gameStatePanel.setBounds(26, 194, 179, 140);
		graphicalGameFrame.getContentPane().add(gameStatePanel);*/
		
		mazeDisplayPanel = new MazeDisplayPanel(this);
		mazeDisplayPanel.setBounds(0, 0, 203, 167);
		graphicalGameFrame.getContentPane().add(mazeDisplayPanel);
	}
	
	private void prepareComponents() {
		mazeDisplayPanel.setSize(maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE, maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE);
		graphicalGameFrame.setSize(mazeDisplayPanel.getX() + mazeDisplayPanel.getWidth(),
										mazeDisplayPanel.getY() + mazeDisplayPanel.getHeight() + 45);
		instructionsLabel.setBounds(0, graphicalGameFrame.getHeight() - 20, graphicalGameFrame.getWidth(), 20);
		
		//Gets the screenSize to center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		graphicalGameFrame.setLocation((screenSize.width - graphicalGameFrame.getWidth())/2,
											(screenSize.height - graphicalGameFrame.getHeight())/2);
	}
	
	public void nextTurn(Direction direction){
		maze.nextTurn(direction);
		mazeDisplayPanel.repaint();

		gameStatePanel.updateState(maze.getGameState());

		if(maze.getGameState() == GameState.RUNNING) {
			instructionsLabel.setText("Ready to play!");
		} else {
			disableMovementButtons();
			instructionsLabel.setText("Game over.");
		}

		/*if (maze.nextTurn(direction)==false){
			if (maze.getObstacle()=='X')
				if(MazeGraphics.wall != null)
					g.drawImage(MazeGraphics.wall, 0, 0, null);
				
					
				break;
				
			else
				instructionsLabel.setText("You cannot pass the exit until you kil all the dragons");
		}
		else
			if(maze.getGameState() == GameState.RUNNING) {
				instructionsLabel.setText("Next move?\n");
			} else if (maze.getGameState() == GameState.DRAGON_WIN) {
				disableMovementButtons();
				instructionsLabel.setText("Game over! Dragon won.\n");
			}else {
				disableMovementButtons();
				instructionsLabel.setText("Game over! You won.\n");
			}*/
	}

	private void disableMovementButtons() {
		/*upButton.setEnabled(false);
		downButton.setEnabled(false);
		rightButton.setEnabled(false);
		leftButton.setEnabled(false);*/
	}

	public Maze getMaze() {
		return maze;
	}

	//Closes the window.
	public void close() {
		graphicalGameFrame.setVisible(false);
		graphicalGameFrame.dispatchEvent(new WindowEvent(graphicalGameFrame, WindowEvent.WINDOW_CLOSING));
	}
}
