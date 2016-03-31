package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import maze.logic.Game.Direction;
import maze.logic.Game.GameState;
import maze.logic.Maze;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MazeGameTextMode {

	private JFrame MazeGameTextModeWindow;
	private JTextArea mazeTextArea;
	private JLabel mazeGameStateLabel;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;
	private Maze maze;
	
	
	public MazeGameTextMode(Maze maze) {
		initialize();
		this.maze = maze;
		mazeGameStateLabel.setText("Ready to play!");

		enableMovementButtons();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MazeGameTextModeWindow = new JFrame();
		MazeGameTextModeWindow.setResizable(false);
		MazeGameTextModeWindow.setTitle("Play Window");
		MazeGameTextModeWindow.setBounds(100, 100, 398, 268);
		MazeGameTextModeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MazeGameTextModeWindow.setVisible(true);
		MazeGameTextModeWindow.setEnabled(true);
		MazeGameTextModeWindow.getContentPane().setLayout(null);

		mazeTextArea = new JTextArea();
		mazeTextArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeTextArea.setText(maze.toString());
		mazeTextArea.setVisible(true);
		mazeTextArea.setEnabled(true);
		int charWidth =	mazeTextArea.getFontMetrics(mazeTextArea.getFont()).stringWidth(" ");
		int charHeight = mazeTextArea.getFontMetrics(mazeTextArea.getFont()).getHeight();
		mazeTextArea.setBounds(211, 28, mazeTextArea.getColumns()*charWidth, mazeTextArea.getLineCount()*charHeight);
		MazeGameTextModeWindow.getContentPane().add(mazeTextArea);
		MazeGameTextModeWindow.setSize(mazeTextArea.getX() + mazeTextArea.getWidth(), 
				mazeTextArea.getY() + mazeTextArea.getHeight());
		
		JButton finishGameButton = new JButton("Finish Game");
		
		finishGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		finishGameButton.setBounds(22, 26, 89, 23);
		finishGameButton.setVisible(true);
		MazeGameTextModeWindow.getContentPane().add(finishGameButton);

		JButton upButton = new JButton("UP");
		upButton.setVisible(true);
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.UP);
			}
		});
		upButton.setBounds(50, 98, 89, 23);
		MazeGameTextModeWindow.getContentPane().add(upButton);

		JButton downButton = new JButton("DOWN");
		downButton.setVisible(true);
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.DOWN);
			}
		});
		downButton.setBounds(50, 164, 89, 23);
		MazeGameTextModeWindow.getContentPane().add(downButton);

		JButton leftButton = new JButton("LEFT");
		leftButton.setVisible(true);
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		leftButton.setBounds(0, 130, 89, 23);
		MazeGameTextModeWindow.getContentPane().add(leftButton);

		JButton rightButton = new JButton("RIGHT");
		rightButton.setVisible(true);
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		rightButton.setBounds(99, 130, 89, 23);
		MazeGameTextModeWindow.getContentPane().add(rightButton);

		JLabel mazeGameStateLabel = new JLabel("");
		mazeGameStateLabel.setVisible(true);
		mazeGameStateLabel.setBounds(50, 207, 46, 14);
		MazeGameTextModeWindow.getContentPane().add(mazeGameStateLabel);
	}

	public void nextTurn(Direction direction){

		maze.nextTurn(direction);
		mazeTextArea.setText(maze.toString());
		mazeGameStateLabel.setText("Moves: " + maze.getNumMoves() + "\n");
		
		if (maze.nextTurn(direction)==false){
			if (maze.getObstacle()=='X')
				mazeGameStateLabel.setText("You cannot move into a wall. Try another direction!");
			else
				mazeGameStateLabel.setText("You cannot pass the exit until you kil all the dragons");
		}
		else
			if(maze.getGameState() == GameState.RUNNING) {
			mazeGameStateLabel.setText("Next move?\n");
		} else if (maze.getGameState() == GameState.DRAGON_WIN) {
			disableMovementButtons();
			mazeGameStateLabel.setText("Game over! Dragon won.\n");
		}else {
			disableMovementButtons();
			mazeGameStateLabel.setText("Game over! You won.\n");
		}
		mazeTextArea.requestFocus();
	}

	private void enableMovementButtons() {
		upButton.setEnabled(true);
		downButton.setEnabled(true);
		rightButton.setEnabled(true);
		leftButton.setEnabled(true);
	}
	
	private void disableMovementButtons() {
		upButton.setEnabled(false);
		downButton.setEnabled(false);
		rightButton.setEnabled(false);
		leftButton.setEnabled(false);

	}
}
