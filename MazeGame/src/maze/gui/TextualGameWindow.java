package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import maze.logic.Game.Direction;
import maze.logic.Maze;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TextualGameWindow {

	private JFrame MazeGameTextModeWindow;
	private JTextArea mazeTextArea;
	private JLabel gameStateLabel;
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
		//mazeTextArea.setText(maze.toString());
		mazeTextArea.setText("ola");

		MazeGameTextModeWindow.setResizable(true);

		//FIXME: Dynamic sizing not working properly.
		int charWidth =	mazeTextArea.getFontMetrics(mazeTextArea.getFont()).stringWidth(" ");
		int charHeight = mazeTextArea.getFontMetrics(mazeTextArea.getFont()).getHeight();
		int minY = Math.max(downButton.getY() + downButton.getHeight(), 
				mazeTextArea.getX() + mazeTextArea.getLineCount()*charHeight+50);
		mazeTextArea.setSize( mazeTextArea.getColumns()*charWidth, mazeTextArea.getLineCount()*charHeight);
		MazeGameTextModeWindow.setSize(mazeTextArea.getX() + mazeTextArea.getWidth(), minY);


		gameStateLabel.setText("Ready to play!\n");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MazeGameTextModeWindow = new JFrame();
		MazeGameTextModeWindow.setResizable(false);
		MazeGameTextModeWindow.setTitle("Play Window");
		MazeGameTextModeWindow.setBounds(100, 100, 435, 357);
		MazeGameTextModeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MazeGameTextModeWindow.getContentPane().setLayout(null);
		MazeGameTextModeWindow.setEnabled(true);
		MazeGameTextModeWindow.setVisible(true);

		mazeTextArea = new JTextArea();
		mazeTextArea.setBounds(211, 28, 55, 15);
		mazeTextArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeTextArea.setVisible(true);
		mazeTextArea.setEnabled(true);
		MazeGameTextModeWindow.getContentPane().add(mazeTextArea);

		gameStateLabel = new JLabel();
		gameStateLabel.setBounds(213, 163, 148, 79);
		MazeGameTextModeWindow.getContentPane().add(gameStateLabel);

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

		upButton = new JButton("UP");
		upButton.setBounds(50, 98, 89, 23);
		upButton.setVisible(true);
		upButton.setEnabled(true);
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.UP);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(upButton);

		downButton = new JButton("DOWN");
		downButton.setBounds(50, 164, 89, 23);
		downButton.setVisible(true);
		downButton.setEnabled(true);
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.DOWN);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(downButton);

		leftButton = new JButton("LEFT");
		leftButton.setBounds(0, 130, 89, 23);
		leftButton.setVisible(true);
		leftButton.setEnabled(true);
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(leftButton);

		rightButton = new JButton("RIGHT");
		rightButton.setBounds(99, 130, 89, 23);
		rightButton.setVisible(true);
		rightButton.setEnabled(true);
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		MazeGameTextModeWindow.getContentPane().add(rightButton);
	}

	public void nextTurn(Direction direction){

		//TODO corrigir contador de moves, statetextarea e movimentaçoes estranhas do heroi

		maze.nextTurn(direction);
		mazeTextArea.setText(maze.toString());
		//mazeGameStateTextArea.setText("Moves: " + maze.getNumMoves() + "\n");

		switch (maze.getGameState()) {
		case RUNNING:
			gameStateLabel.setText("Next move?");
			/*if (maze.getObstacle()=='X')
			mazeGameStateTextArea.setText("You cannot move into a wall. Try another direction!");
		else
			mazeGameStateTextArea.setText("You cannot pass the exit until you kil all the dragons");*/
			break;
		case DRAGON_WIN:
			disableMovementButtons();
			gameStateLabel.setText("Game over! Dragon won.");
			break;
		case HERO_WIN:
			disableMovementButtons();
			gameStateLabel.setText("Game over! You won.");
			break;
		}

		mazeTextArea.requestFocus();
	}

	private void disableMovementButtons() {
		upButton.setEnabled(false);
		downButton.setEnabled(false);
		rightButton.setEnabled(false);
		leftButton.setEnabled(false);
	}
}
