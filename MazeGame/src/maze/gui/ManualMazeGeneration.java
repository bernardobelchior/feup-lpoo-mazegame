package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManualMazeGeneration extends JFrame {

	private JPanel contentPane;
	private char[][] maze;
	private int size;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualMazeGeneration frame = new ManualMazeGeneration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManualMazeGeneration() {
		setResizable(false);
		setTitle("Manual Generation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mazePanel = new JPanel();
		mazePanel.setBounds(84, 11, 359, 285);
		contentPane.add(mazePanel);
		
		JPanel elementsPanel = new ElementPanel();
		elementsPanel.setBounds(10, 11, 64, 285);
		contentPane.add(elementsPanel);
		this.maze = null;
		this.size = 11;
	}
	
	public void setSize(int size) {
		if(size > 4)
			this.size = size;
	}

	
}
