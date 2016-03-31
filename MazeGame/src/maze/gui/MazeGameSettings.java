package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import maze.logic.Game.Direction;
import maze.logic.Game.GameMode;
import maze.logic.Game.GameState;
import maze.logic.Maze;
import maze.logic.RandomMazeGenerator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;

//TODO apagar tudo o que está nesta classe que já nao devia estar
//TODO por esta janela a fechar e abrir as outras automaticamente

public class MazeGameSettings {
	private static final String STATIONARY_DRAGON_TEXT = "Stationary";
	private static final String RANDOM_DRAGON_TEXT = "Random";
	private static final String SLEEPING_DRAGON_TEXT = "Sleeping";
	private static final String TEXT_MAZE="Text Maze";
	private static final String GRAPHICAL_MAZE="Graphical Maze";

	private JFrame mazeGameSettings;
	private JTextField mazeDimensionTextField;
	private JTextField dragonNumberTextField;
	private JButton upButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton leftButton;
	private JLabel instructionsLabel;
	private JTextArea mazeTextArea;
	private JPanel mazeImagePanel;
	private JPanel mazeStatePanel;
	public static MazeGameSettings mazeWindow;
	private MazeGameTextMode textMaze;
	private MazeGameGraphicalMode graphicalMaze;

	private Maze maze;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mazeWindow = new MazeGameSettings();
					mazeWindow.mazeGameSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeGameSettings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		MazeGraphics.loadImages();
		mazeGameSettings = new JFrame();
		mazeGameSettings.setResizable(false);
		mazeGameSettings.setTitle("Game Settings");
		mazeGameSettings.setBounds(100, 100, 450, 493);
		mazeGameSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeGameSettings.getContentPane().setLayout(null);

		mazeTextArea = new JTextArea();
		mazeTextArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeTextArea.setEditable(false);
		mazeTextArea.setBounds(224, 12, 210, 163);
		mazeTextArea.setVisible(false);
		mazeGameSettings.getContentPane().add(mazeTextArea);

		mazeDimensionTextField = new JTextField();
		mazeDimensionTextField.setText("11");
		mazeDimensionTextField.setBounds(114, 14, 93, 14);
		mazeGameSettings.getContentPane().add(mazeDimensionTextField);
		mazeDimensionTextField.setColumns(10);

		JLabel mazeDimensionLabel = new JLabel("Maze Dimension:");
		mazeDimensionLabel.setBounds(10, 11, 84, 20);
		mazeGameSettings.getContentPane().add(mazeDimensionLabel);

		JLabel dragonNumberLabel = new JLabel("Number of Dragons:");
		dragonNumberLabel.setBounds(10, 42, 108, 20);
		mazeGameSettings.getContentPane().add(dragonNumberLabel);

		dragonNumberTextField = new JTextField();
		dragonNumberTextField.setText("1");
		dragonNumberTextField.setColumns(10);
		dragonNumberTextField.setBounds(114, 45, 93, 14);
		mazeGameSettings.getContentPane().add(dragonNumberTextField);

		JLabel dragonModeLabel = new JLabel("Dragon Mode:");
		dragonModeLabel.setBounds(10, 73, 84, 14);
		mazeGameSettings.getContentPane().add(dragonModeLabel);

		JComboBox<String> gameModeComboBox = new JComboBox<String>();
		gameModeComboBox.setBounds(114, 73, 93, 20);
		mazeGameSettings.getContentPane().add(gameModeComboBox);
		gameModeComboBox.addItem(STATIONARY_DRAGON_TEXT);
		gameModeComboBox.addItem(RANDOM_DRAGON_TEXT);
		gameModeComboBox.addItem(SLEEPING_DRAGON_TEXT);
		
		JLabel mazeTypeLabel = new JLabel("Maze Type:");
		mazeTypeLabel.setBounds(10, 114, 68, 14);
		mazeGameSettings.getContentPane().add(mazeTypeLabel);

		JComboBox<String> mazeTypeComboBox = new JComboBox<String>();
		mazeTypeComboBox.setBounds(114, 111, 93, 20);
		mazeGameSettings.getContentPane().add(mazeTypeComboBox);
		mazeTypeComboBox.addItem(TEXT_MAZE);
		mazeTypeComboBox.addItem(GRAPHICAL_MAZE);

		upButton = new JButton("UP");
		upButton.setEnabled(false);
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.UP);		
			}
		});
		upButton.setBounds(65, 214, 71, 23);
		mazeGameSettings.getContentPane().add(upButton);

		downButton = new JButton("DOWN");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.DOWN);
			}
		});
		downButton.setEnabled(false);
		downButton.setBounds(65, 281, 71, 23);
		mazeGameSettings.getContentPane().add(downButton);

		leftButton = new JButton("LEFT");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.LEFT);
			}
		});
		leftButton.setEnabled(false);
		leftButton.setBounds(23, 248, 55, 23);
		mazeGameSettings.getContentPane().add(leftButton);

		rightButton = new JButton("RIGHT");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextTurn(Direction.RIGHT);
			}
		});
		rightButton.setEnabled(false);
		rightButton.setBounds(116, 247, 63, 23);
		mazeGameSettings.getContentPane().add(rightButton);

		instructionsLabel = new JLabel("");
		instructionsLabel.setVisible(false);
		instructionsLabel.setBounds(10, 315, 169, 14);
		mazeGameSettings.getContentPane().add(instructionsLabel);

		JButton generateNewMazeButton = new JButton("Create New Maze");
		generateNewMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int size = 11;

				try{
					size = Integer.parseInt(mazeDimensionTextField.getText());
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(mazeGameSettings, "Invalid maze dimension!\nPlease insert a valid integer.");
					return;
				}

				int dragonNumber=1;

				try{
					dragonNumber= Integer.parseInt(dragonNumberTextField.getText());
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(mazeGameSettings, "Invalid number of dragons!\nPlease insert a valid integer.");
					return;
				}

				RandomMazeGenerator rmg = new RandomMazeGenerator(size, dragonNumber);
				GameMode gameMode = GameMode.STATIONARY;
				
				switch ((String) gameModeComboBox.getSelectedItem()){
				case MazeGameSettings.STATIONARY_DRAGON_TEXT :
					gameMode = GameMode.STATIONARY;
					break;
				case MazeGameSettings.RANDOM_DRAGON_TEXT :
					gameMode = GameMode.RANDOM_MOVEMENT;
					break;
				case MazeGameSettings.SLEEPING_DRAGON_TEXT :
					gameMode = GameMode.SLEEP_RANDOM_MOVEMENT;
					break;
				}

				maze = new Maze(rmg.getMaze(), gameMode);

				if ((String)mazeTypeComboBox.getSelectedItem()==TEXT_MAZE)
					textMaze = new MazeGameTextMode(mazeWindow);
				else
					graphicalMaze = new MazeGameGraphicalMode(mazeWindow);
					
					


				//apartir daqui tem de ir para as outras duas janelas para os construtores
				
				mazeTextArea.setText(maze.toString());
				((MazeDisplayPanel) mazeImagePanel).setMaze(maze);
				mazeImagePanel.repaint();
				mazeStatePanel.repaint();

				instructionsLabel.setText("Ready to play!");

				enableMovementButtons();
				mazeImagePanel.setBounds(mazeImagePanel.getX(), mazeImagePanel.getY(),
						maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE, maze.getMazeDimension()*MazeGraphics.TEXTURE_SIZE);
				mazeGameSettings.setBounds(0, 0,
						mazeImagePanel.getX() + mazeImagePanel.getWidth() + 30,
						Math.max(mazeImagePanel.getY() + mazeImagePanel.getHeight() + 50, mazeStatePanel.getX() + mazeStatePanel.getHeight()));
				mazeImagePanel.requestFocus();
			}
		});

		generateNewMazeButton.setBounds(42, 152, 137, 23);
		mazeGameSettings.getContentPane().add(generateNewMazeButton);

		mazeImagePanel = new MazeDisplayPanel();
		mazeImagePanel.setBounds(224, 12, 210, 153);

		mazeGameSettings.getContentPane().add(mazeImagePanel);

		mazeStatePanel = new MazeStateDisplayPanel();
		mazeStatePanel.setBounds(23, 315, 156, 128);
		mazeGameSettings.getContentPane().add(mazeStatePanel);

	}

	public void nextTurn(Direction direction){
		maze.nextTurn(direction);
		mazeTextArea.setText(maze.toString());
		mazeImagePanel.repaint();

		((MazeStateDisplayPanel) mazeStatePanel).updateState(maze.getGameState());
		mazeStatePanel.repaint();

		if(maze.getGameState() == GameState.RUNNING) {
			instructionsLabel.setText("Ready to play!");
		} else {
			disableMovementButtons();
			instructionsLabel.setText("Game over.");
		}

		mazeImagePanel.requestFocus();
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

	public Maze getMaze(){
		return maze;
	}
}
