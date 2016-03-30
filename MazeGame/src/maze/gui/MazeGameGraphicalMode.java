package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class MazeGameGraphicalMode {

	private JFrame PlayWindowGraphicalMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGameGraphicalMode window = new MazeGameGraphicalMode();
					window.PlayWindowGraphicalMode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGameGraphicalMode() {
		initialize();
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(273, 11, 275, 176);
		PlayWindowGraphicalMode.getContentPane().add(textArea);
		
		JButton button = new JButton("Finish Game");
		button.setForeground(Color.RED);
		button.setFont(new Font("Stencil", Font.BOLD, 15));
		button.setBackground(Color.RED);
		button.setBounds(26, 345, 179, 44);
		PlayWindowGraphicalMode.getContentPane().add(button);
		
		JButton button_1 = new JButton("UP");
		button_1.setBounds(74, 19, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("DOWN");
		button_2.setBounds(74, 102, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("LEFT");
		button_3.setBounds(26, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("RIGHT");
		button_4.setBounds(116, 60, 89, 37);
		PlayWindowGraphicalMode.getContentPane().add(button_4);
		
		JLabel label = new JLabel("");
		label.setBounds(26, 169, 179, 14);
		PlayWindowGraphicalMode.getContentPane().add(label);
		
		JPanel mazeStatePanel = new JPanel();
		mazeStatePanel.setBounds(26, 194, 179, 140);
		PlayWindowGraphicalMode.getContentPane().add(mazeStatePanel);
	}

}
