package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import maze.logic.Game.Direction;
import maze.logic.Game.GameState;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeGameTextMode {

	private JFrame PlayWindowTextMode;
	private JTextArea mazeTextArea;
	public static MazeGameSettings mazeWindow=new MazeGameSettings();
	private JLabel instructionsLabel;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGameTextMode window = new MazeGameTextMode(mazeWindow);
					window.PlayWindowTextMode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGameTextMode(MazeGameSettings mazeWindow) {
		initialize();
		this.mazeWindow=mazeWindow;
		
		mazeTextArea.setText(mazeWindow.getMaze().toString());

		instructionsLabel.setText("Ready to play!");

		enableMovementButtons();
		
		//TODO tirar estes comentários depois de ter feito o design da janela
		/*PlayWindowTextMode.setBounds(0, 0,
				mazeTextArea.getX() + mazeTextArea.getWidth() + 30,
				Math.max(mazeTextArea.getY() + mazeTextArea.getHeight() + 50, instructionsLabel.getY() + instructionsLabel.getHeight()+50));
		mazeTextArea.requestFocus();*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PlayWindowTextMode = new JFrame();
		PlayWindowTextMode.setResizable(false);
		PlayWindowTextMode.setTitle("Play Window");
		PlayWindowTextMode.setBounds(100, 100, 398, 268);
		PlayWindowTextMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayWindowTextMode.getContentPane().setLayout(null);

		JTextArea mazeTextArea = new JTextArea();
		mazeTextArea.setBounds(292, 25, 275, 176);
		PlayWindowTextMode.getContentPane().add(mazeTextArea);

		JButton finishGameButton = new JButton("Finish Game");
		finishGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		finishGameButton.setBounds(22, 26, 89, 23);
		PlayWindowTextMode.getContentPane().add(finishGameButton);

		JButton upButton = new JButton("UP");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.UP);
			}
		});
		upButton.setBounds(50, 98, 89, 23);
		PlayWindowTextMode.getContentPane().add(upButton);

		JButton downButton = new JButton("DOWN");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.DOWN);
			}
		});
		downButton.setBounds(50, 164, 89, 23);
		PlayWindowTextMode.getContentPane().add(downButton);

		JButton leftButton = new JButton("LEFT");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		leftButton.setBounds(0, 130, 89, 23);
		PlayWindowTextMode.getContentPane().add(leftButton);

		JButton rightButton = new JButton("RIGHT");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		rightButton.setBounds(99, 130, 89, 23);
		PlayWindowTextMode.getContentPane().add(rightButton);

		JLabel instructionsLabel = new JLabel("");
		instructionsLabel.setBounds(50, 207, 46, 14);
		PlayWindowTextMode.getContentPane().add(instructionsLabel);
	}

	public void nextTurn(Direction direction){

		mazeWindow.getMaze().nextTurn(direction);
		mazeTextArea.setText(mazeWindow.getMaze().toString());
		
		//TODO fazer update de estados na etiqueta de instruçoes e nao no painel de estado
		/*((MazeStateDisplayPanel) mazeStatePanel).updateState(maze.getGameState());
		mazeStatePanel.repaint();*/
		
		if(mazeWindow.getMaze().getGameState() == GameState.RUNNING) {
			instructionsLabel.setText("Ready to play!");
		} else {
			disableMovementButtons();
			instructionsLabel.setText("Game over.");
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
