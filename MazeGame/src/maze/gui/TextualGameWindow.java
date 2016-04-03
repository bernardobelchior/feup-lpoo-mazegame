package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import maze.logic.Game.Direction;
import maze.logic.Maze;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class TextualGameWindow {

	private JFrame textualGameFrame;
	private JTextArea mazeTextArea;
	private JLabel gameInfoLabel;
	private JLabel gameStateImage;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;
	private Maze maze;

	public TextualGameWindow(Maze maze) {
		this.maze = maze;
		initialize();
		mazeTextArea.setText(maze.toString());

		textualGameFrame.setResizable(true);

		//FIXME: Dynamic sizing not working properly.
		int charWidth =	mazeTextArea.getFontMetrics(mazeTextArea.getFont()).stringWidth(" ");
		int charHeight = mazeTextArea.getFontMetrics(mazeTextArea.getFont()).getHeight();
		int minY = Math.max(downButton.getY() + downButton.getHeight(), 
				mazeTextArea.getX() + mazeTextArea.getLineCount()*charHeight+50);
		mazeTextArea.setSize( mazeTextArea.getColumns()*charWidth, mazeTextArea.getLineCount()*charHeight);
		textualGameFrame.setSize(mazeTextArea.getX() + mazeTextArea.getWidth(), minY);


		gameInfoLabel.setText("Ready to play!\n");
		adjustWindowToScreen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		textualGameFrame = new JFrame();
		textualGameFrame.setTitle("Play Window");
		textualGameFrame.setBounds(100, 100, 200, 200);
		textualGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textualGameFrame.getContentPane().setLayout(new BorderLayout());
		textualGameFrame.setEnabled(true);
		textualGameFrame.setVisible(true);
		textualGameFrame.setResizable(false);

		mazeTextArea = new JTextArea();
		mazeTextArea.setBounds(211, 28, 55, 15);
		mazeTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
		mazeTextArea.setVisible(true);
		mazeTextArea.setEnabled(true);
		mazeTextArea.setEditable(false);
		textualGameFrame.getContentPane().add(mazeTextArea, BorderLayout.CENTER);

		gameInfoLabel = new JLabel();
		gameInfoLabel.setBounds(213, 163, 148, 79);
		textualGameFrame.getContentPane().add(gameInfoLabel, BorderLayout.PAGE_END);

		JPanel gameInterfacePanel = new JPanel();
		gameInterfacePanel.setLayout(new GridBagLayout());
		textualGameFrame.getContentPane().add(gameInterfacePanel, BorderLayout.LINE_START);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		upButton = new JButton("UP");
		upButton.setVisible(true);
		upButton.setEnabled(true);
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.UP);
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 0;
		gameInterfacePanel.add(upButton, gbc);

		downButton = new JButton("DOWN");
		downButton.setVisible(true);
		downButton.setEnabled(true);
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextTurn(Direction.DOWN);
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 2;
		gameInterfacePanel.add(downButton, gbc);

		leftButton = new JButton("LEFT");
		//leftButton.setBounds(0, 130, 89, 23);
		leftButton.setVisible(true);
		leftButton.setEnabled(true);
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gameInterfacePanel.add(leftButton, gbc);

		rightButton = new JButton("RIGHT");
		//rightButton.setBounds(99, 130, 89, 23);
		rightButton.setVisible(true);
		rightButton.setEnabled(true);
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 1;
		gameInterfacePanel.add(rightButton, gbc);
		
		gameStateImage = new JLabel();
		gameStateImage.setIcon(new ImageIcon(MazeGraphics.wall));
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.weighty = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		gameInterfacePanel.add(gameStateImage, gbc);
	}

	public void nextTurn(Direction direction){
		maze.nextTurn(direction);
		mazeTextArea.setText(maze.toString());

		switch (maze.getGameState()) {
		case RUNNING:
			gameInfoLabel.setText("Next move?");
			/*if (maze.getObstacle()=='X')
			mazeGameStateTextArea.setText("You cannot move into a wall. Try another direction!");
		else
			mazeGameStateTextArea.setText("You cannot pass the exit until you kil all the dragons");*/
			break;
		case DRAGON_WIN:
			disableMovementButtons();
			gameStateImage.setIcon(new ImageIcon(MazeGraphics.dragonAwaken));
			gameInfoLabel.setText("Game over! Dragon won.");
			break;
		case HERO_WIN:
			disableMovementButtons();
			gameStateImage.setIcon(new ImageIcon(MazeGraphics.heroArmed));
			gameInfoLabel.setText("Game over! You won.");
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
	
	private void adjustWindowToScreen() {
		int width = Math.min(
				textualGameFrame.getInsets().left + textualGameFrame.getInsets().right + mazeTextArea.getX() + mazeTextArea.getPreferredSize().width,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
		int height = Math.min(
				textualGameFrame.getInsets().top + textualGameFrame.getInsets().bottom + gameInfoLabel.getHeight() +
				Math.max(gameStateImage.getY() + gameStateImage.getHeight(),
						 mazeTextArea.getY() + mazeTextArea.getPreferredSize().height),
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		textualGameFrame.setSize(width, height);
		MazeGraphics.centerFrame(textualGameFrame);
		textualGameFrame.setLocation(textualGameFrame.getLocation().x, 0);
	}
}
