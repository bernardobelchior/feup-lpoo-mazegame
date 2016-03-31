package maze.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Maze;

import java.awt.Color;
import java.awt.Font;

public class GraphicalGameWindow {

	private JFrame PlayWindowGraphicalMode;
	private Maze maze;

	/**
	 * Create the application.
	 */
	public GraphicalGameWindow(Maze maze) {
		initialize();
		this.maze = maze;
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
		
		JButton finishGameButton = new JButton("Finish Game");
		finishGameButton.setForeground(Color.RED);
		finishGameButton.setFont(new Font("Stencil", Font.BOLD, 15));
		finishGameButton.setBackground(Color.RED);
		finishGameButton.setBounds(26, 345, 179, 44);
		PlayWindowGraphicalMode.getContentPane().add(finishGameButton);
		
		JButton upButton = new JButton("UP");
		upButton.setBounds(74, 19, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(upButton);
		
		JButton downButton = new JButton("DOWN");
		downButton.setBounds(74, 102, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(downButton);
		
		JButton leftButton = new JButton("LEFT");
		leftButton.setBounds(26, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(leftButton);
		
		JButton rightButton = new JButton("RIGHT");
		rightButton.setBounds(116, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(rightButton);
		
		JLabel instructionsLabel = new JLabel("");
		instructionsLabel.setBounds(26, 169, 179, 14);
		PlayWindowGraphicalMode.getContentPane().add(instructionsLabel);
		
		JPanel mazeStatePanel = new JPanel();
		mazeStatePanel.setBounds(26, 194, 179, 140);
		PlayWindowGraphicalMode.getContentPane().add(mazeStatePanel);
		
		JPanel mazeImagePanel = new JPanel();
		mazeImagePanel.setBounds(236, 19, 203, 167);
		PlayWindowGraphicalMode.getContentPane().add(mazeImagePanel);
	}
}
