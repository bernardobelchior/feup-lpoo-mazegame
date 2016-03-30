package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MazeGameTextMode {

	private JFrame PlayWindowTextMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGameTextMode window = new MazeGameTextMode();
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
	public MazeGameTextMode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		PlayWindowTextMode = new JFrame();
		PlayWindowTextMode.setTitle("Play Window");
		PlayWindowTextMode.setResizable(false);
		PlayWindowTextMode.setBounds(100, 100, 450, 300);
		PlayWindowTextMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayWindowTextMode.getContentPane().setLayout(null);
		
		JTextArea mazeTextArea = new JTextArea();
		mazeTextArea.setBounds(292, 25, 275, 176);
		PlayWindowTextMode.getContentPane().add(mazeTextArea);
		
		JButton finishGameButton = new JButton("Finish Game");
		finishGameButton.setBounds(22, 26, 89, 23);
		PlayWindowTextMode.getContentPane().add(finishGameButton);
		
		JButton upButton = new JButton("UP");
		upButton.setBounds(50, 98, 89, 23);
		PlayWindowTextMode.getContentPane().add(upButton);
		
		JButton downButton = new JButton("DOWN");
		downButton.setBounds(50, 164, 89, 23);
		PlayWindowTextMode.getContentPane().add(downButton);
		
		JButton leftButton = new JButton("LEFT");
		leftButton.setBounds(0, 130, 89, 23);
		PlayWindowTextMode.getContentPane().add(leftButton);
		
		JButton rightButton = new JButton("RIGHT");
		rightButton.setBounds(99, 130, 89, 23);
		PlayWindowTextMode.getContentPane().add(rightButton);
		
		JLabel instructionsLabel = new JLabel("");
		instructionsLabel.setBounds(50, 207, 46, 14);
		PlayWindowTextMode.getContentPane().add(instructionsLabel);
	}
}
