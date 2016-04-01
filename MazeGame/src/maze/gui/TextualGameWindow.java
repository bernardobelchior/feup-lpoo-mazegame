package maze.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import maze.logic.Game.Direction;
import maze.logic.Game.GameState;
import maze.logic.Maze;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TextualGameWindow {

	private JFrame MazeGameTextModeWindow;
	private JTextArea mazeTextArea;
	private JTextArea mazeGameStateTextArea;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;
	private Maze maze;
	
	
//TODO fix game because the hero is not being killed by the dragon

	public TextualGameWindow(Maze maze, JFrame mazeGameSettings) {
		initialize();
		// TODO por janela dos settings a fechar quando esta abre mazeGameSettings.dispatchEvent(new WindowEvent(mazeGameSettings, WindowEvent.WINDOW_CLOSING));
		this.maze = maze;
		mazeTextArea.setText(maze.toString());
		
		mazeGameStateTextArea.setText("Ready to play!\n");

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
		MazeGameTextModeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MazeGameTextModeWindow.getContentPane().setLayout(null);
		MazeGameTextModeWindow.setEnabled(true);
		MazeGameTextModeWindow.setVisible(true);
		
		mazeTextArea = new JTextArea();
		mazeTextArea.setBounds(211, 28, 150, 105);
		mazeTextArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeTextArea.setVisible(true);
		mazeTextArea.setEnabled(true);
		MazeGameTextModeWindow.getContentPane().add(mazeTextArea);
		
		mazeGameStateTextArea = new JTextArea();
		mazeGameStateTextArea.setBounds(213, 163, 148, 79);
		MazeGameTextModeWindow.getContentPane().add(mazeGameStateTextArea);

		JButton finishGameButton = new JButton("Finish Game");
		finishGameButton.setBounds(22, 26, 89, 23);

		finishGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		finishGameButton.setVisible(true);
		finishGameButton.setEnabled(true);
		MazeGameTextModeWindow.getContentPane().add(finishGameButton);

		JButton upButton = new JButton("UP");
		upButton.setBounds(50, 98, 89, 23);
		upButton.setVisible(true);
		upButton.setEnabled(true);
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.UP);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(upButton);

		JButton downButton = new JButton("DOWN");
		downButton.setBounds(50, 164, 89, 23);
		downButton.setVisible(true);
		downButton.setEnabled(true);
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.DOWN);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(downButton);

		JButton leftButton = new JButton("LEFT");
		leftButton.setBounds(0, 130, 89, 23);
		leftButton.setVisible(true);
		leftButton.setEnabled(true);
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(leftButton);

		JButton rightButton = new JButton("RIGHT");
		rightButton.setBounds(99, 130, 89, 23);
		rightButton.setVisible(true);
		rightButton.setEnabled(true);
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(rightButton);

		//FIXME: Dynamic sizing not working properly.
		int charWidth =	mazeTextArea.getFontMetrics(mazeTextArea.getFont()).stringWidth(" ");
		int charHeight = mazeTextArea.getFontMetrics(mazeTextArea.getFont()).getHeight();
		int minY = Math.min(downButton.getY() + downButton.getHeight(), 
				mazeTextArea.getX() + mazeTextArea.getLineCount()*charHeight+50);
		MazeGameTextModeWindow.setSize(412, 292);
		MazeGameTextModeWindow.setResizable(true);
	}

	public void nextTurn(Direction direction){

		//TODO corrigir contador de moves, statetextarea e movimentaçoes estranhas do heroi
		
		maze.nextTurn(direction);
		mazeTextArea.setText(maze.toString());
		mazeGameStateTextArea.setText("Moves: " + maze.getNumMoves() + "\n");

		if (maze.nextTurn(direction)==false){
			if (maze.getObstacle()=='X')
				mazeGameStateTextArea.setText("You cannot move into a wall. Try another direction!");
			else
				mazeGameStateTextArea.setText("You cannot pass the exit until you kil all the dragons");
		}
		else
			if(maze.getGameState() == GameState.RUNNING) {
				mazeGameStateTextArea.setText("Next move?\n");
			} else if (maze.getGameState() == GameState.DRAGON_WIN) {
				disableMovementButtons();
				mazeGameStateTextArea.setText("Game over! Dragon won.\n");
			}else {
				disableMovementButtons();
				mazeGameStateTextArea.setText("Game over! You won.\n");
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
