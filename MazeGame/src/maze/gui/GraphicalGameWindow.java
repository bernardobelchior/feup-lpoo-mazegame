package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import maze.logic.Maze;
import maze.logic.Game.Direction;
import maze.logic.Game.GameState;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;

public class GraphicalGameWindow {
	private JFrame graphicalGameFrame;
	private JLabel instructionsLabel;
	private MazeDisplayPanel mazeDisplayPanel;
	private JScrollPane mazeDisplayScrollPane;
	private GameStateDisplayPanel gameStatePanel = new GameStateDisplayPanel();

	private Maze maze;

	/**
	 * Create the application.
	 */
	public GraphicalGameWindow(Maze maze) {
		this.maze = maze;
		initialize();
		adjustWindowToScreen();
		
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
		graphicalGameFrame.setLayout(new BorderLayout());
		graphicalGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		graphicalGameFrame.getContentPane().setLayout(new BorderLayout());	
		graphicalGameFrame.setEnabled(true);
		graphicalGameFrame.setResizable(false);
		graphicalGameFrame.setVisible(true);
			
		mazeDisplayPanel = new MazeDisplayPanel(this);
		mazeDisplayPanel.setPreferredSize(new Dimension (maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE, maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE));

		mazeDisplayScrollPane = new JScrollPane(mazeDisplayPanel);
		mazeDisplayScrollPane.setBounds(0, 0, 300, 300);
		graphicalGameFrame.getContentPane().add(mazeDisplayScrollPane, BorderLayout.CENTER);

		instructionsLabel = new JLabel("");
		instructionsLabel.setSize(10, 16);
		instructionsLabel.setText("Ready to play!");
		graphicalGameFrame.getContentPane().add(instructionsLabel, BorderLayout.PAGE_END);		
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
	
	private void adjustWindowToScreen() {
		int width = Math.min(
				graphicalGameFrame.getInsets().left + graphicalGameFrame.getInsets().right + mazeDisplayScrollPane.getPreferredSize().width + mazeDisplayScrollPane.getVerticalScrollBar().getSize().width,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
		int height = Math.min(
				graphicalGameFrame.getInsets().top + graphicalGameFrame.getInsets().bottom + mazeDisplayScrollPane.getPreferredSize().height + mazeDisplayScrollPane.getHorizontalScrollBar().getSize().height + instructionsLabel.getSize().height,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		graphicalGameFrame.setSize(width, height);
		MazeGraphics.centerFrame(graphicalGameFrame);
		graphicalGameFrame.setLocation(graphicalGameFrame.getLocation().x, 0);
	}
}
