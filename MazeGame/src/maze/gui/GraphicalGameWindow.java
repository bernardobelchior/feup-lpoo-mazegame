package maze.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Maze;
import maze.logic.Game.Direction;
import maze.logic.Game.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GraphicalGameWindow {

	private JFrame PlayWindowGraphicalMode;
	private Maze maze;
	private JPanel mazeImagePanel;
	private JPanel mazeStatePanel;
	private JLabel instructionsLabel;
	private JButton upButton, downButton, leftButton, rightButton;
	private MazeDisplayPanel mazeDisplayPanel;
	private GameStateDisplayPanel gameStatePanel = new GameStateDisplayPanel();

	/**
	 * Create the application.
	 */
	public GraphicalGameWindow(Maze maze) {
		initialize();
		
		//TODO depois de por isto a funcionar testar movimento com teclas
		//depois de tudo funcionar tratar da parte visual
		
		// TODO por janela dos settings a fechar quando esta abre mazeGameSettings.dispatchEvent(new WindowEvent(mazeGameSettings, WindowEvent.WINDOW_CLOSING));
		//eventulmente por um botao back  para voltar aos settings
		this.maze = maze;
		mazeDisplayPanel=new MazeDisplayPanel();
		mazeDisplayPanel.setMaze(maze);
		mazeImagePanel.repaint();
		mazeStatePanel.repaint();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PlayWindowGraphicalMode = new JFrame();
		PlayWindowGraphicalMode.setTitle("Play Window");
		PlayWindowGraphicalMode.setBounds(100, 100, 622, 482);
		PlayWindowGraphicalMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayWindowGraphicalMode.getContentPane().setLayout(null);
		PlayWindowGraphicalMode.setEnabled(true);
		PlayWindowGraphicalMode.setVisible(true);
		
		JButton finishGameButton = new JButton("Finish Game");
		finishGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		finishGameButton.setForeground(Color.RED);
		finishGameButton.setFont(new Font("Stencil", Font.BOLD, 15));
		finishGameButton.setBackground(Color.RED);
		finishGameButton.setBounds(26, 345, 179, 44);
		PlayWindowGraphicalMode.getContentPane().add(finishGameButton);
		
		upButton = new JButton("UP");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.UP);
			}
		});
		upButton.setBounds(74, 19, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(upButton);
		
		downButton = new JButton("DOWN");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.DOWN);
			}
		});
		downButton.setBounds(74, 102, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(downButton);
		
		leftButton = new JButton("LEFT");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		leftButton.setBounds(26, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(leftButton);
		
		rightButton = new JButton("RIGHT");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		rightButton.setBounds(116, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(rightButton);
		
		instructionsLabel = new JLabel("");
		instructionsLabel.setBounds(26, 169, 179, 14);
		PlayWindowGraphicalMode.getContentPane().add(instructionsLabel);
		
		mazeStatePanel = new JPanel();
		mazeStatePanel.setBounds(26, 194, 179, 140);
		PlayWindowGraphicalMode.getContentPane().add(mazeStatePanel);
		mazeStatePanel.setEnabled(true);
		mazeStatePanel.setVisible(true);
		
		mazeImagePanel = new JPanel();
		mazeImagePanel.setBounds(236, 19, 203, 167);
		PlayWindowGraphicalMode.getContentPane().add(mazeImagePanel);
		mazeImagePanel.setVisible(true);
		mazeImagePanel.setEnabled(true);
	}
	
	public void nextTurn(Direction direction){
		maze.nextTurn(direction);
		mazeImagePanel.repaint();

		gameStatePanel.updateState(maze.getGameState());
		mazeStatePanel.repaint();

		if(maze.getGameState() == GameState.RUNNING) {
			instructionsLabel.setText("Ready to play!");
		} else {
			disableMovementButtons();
			instructionsLabel.setText("Game over.");
		}

		mazeImagePanel.requestFocus();
		
		/*maze.nextTurn(direction);
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
		mazeTextArea.requestFocus(); */
	}


	private void disableMovementButtons() {
		upButton.setEnabled(false);
		downButton.setEnabled(false);
		rightButton.setEnabled(false);
		leftButton.setEnabled(false);
	}

}
