package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import maze.cli.GameInterface;
import maze.logic.Game.GameMode;
import maze.logic.Maze;
import maze.logic.RandomMazeGenerator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;


public class GameSettingsWindow {
	private static final String STATIONARY_DRAGON_TEXT = "Stationary";
	private static final String RANDOM_DRAGON_TEXT = "Random";
	private static final String SLEEPING_DRAGON_TEXT = "Sleeping";
	private static final String CONSOLE_MAZE = "Console Maze";
	private static final String TEXT_MAZE = "Text Maze";
	private static final String GRAPHICAL_MAZE = "Graphical Maze";
	private static final String MANUAL_MAZE = "Manual Maze";

	public JFrame mazeGameSettings;
	public static GameSettingsWindow mazeWindow;
	private Maze maze;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mazeWindow = new GameSettingsWindow();
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
	public GameSettingsWindow() {
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

		JLabel mazeDimensionLabel = new JLabel("Maze Dimension:");
		mazeDimensionLabel.setBounds(10, 11, 84, 20);
		mazeGameSettings.getContentPane().add(mazeDimensionLabel);

		JLabel dragonNumberLabel = new JLabel("Number of Dragons:");
		dragonNumberLabel.setBounds(10, 42, 108, 20);
		mazeGameSettings.getContentPane().add(dragonNumberLabel);

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
		mazeTypeComboBox.addItem(GRAPHICAL_MAZE);
		mazeTypeComboBox.addItem(TEXT_MAZE);
		mazeTypeComboBox.addItem(CONSOLE_MAZE);
		mazeTypeComboBox.addItem(MANUAL_MAZE);
		
		SpinnerNumberModel model = new SpinnerNumberModel(11, 5, 50, 1);
		JSpinner mazeDimensionSpinner = new JSpinner(model);
		mazeDimensionSpinner.setBounds(114, 11, 50, 20);
		mazeGameSettings.getContentPane().add(mazeDimensionSpinner);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 10, 1);
		JSpinner dragonNumberSpinner = new JSpinner(model1);
		dragonNumberSpinner.setBounds(114, 42, 50, 20);
		mazeGameSettings.getContentPane().add(dragonNumberSpinner);
		
		JButton generateNewMazeButton = new JButton("Create New Maze");
		generateNewMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int size;

				try{
					size = ((Integer)mazeDimensionSpinner.getValue()).intValue();
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(mazeGameSettings, "Invalid maze dimension!\nPlease insert a valid integer.");
					return;
				}

				int dragonNumber;

				try{
					dragonNumber= ((Integer)dragonNumberSpinner.getValue()).intValue();
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(mazeGameSettings, "Invalid number of dragons!\nPlease insert a valid integer.");
					return;
				}

				RandomMazeGenerator rmg = new RandomMazeGenerator(size, dragonNumber);
				GameMode gameMode = GameMode.STATIONARY;
				
				switch ((String) gameModeComboBox.getSelectedItem()){
				case GameSettingsWindow.STATIONARY_DRAGON_TEXT :
					gameMode = GameMode.STATIONARY;
					break;
				case GameSettingsWindow.RANDOM_DRAGON_TEXT :
					gameMode = GameMode.RANDOM_MOVEMENT;
					break;
				case GameSettingsWindow.SLEEPING_DRAGON_TEXT :
					gameMode = GameMode.SLEEP_RANDOM_MOVEMENT;
					break;
				}

				//FIXME: This must be either a random maze
				//		 or a manually generated one.
				maze = new Maze(rmg.getMaze(), gameMode);
			
				if ((String)mazeTypeComboBox.getSelectedItem() == GRAPHICAL_MAZE){
					new GraphicalGameWindow(maze);
				}else if((String)mazeTypeComboBox.getSelectedItem() == TEXT_MAZE){
					new TextualGameWindow(maze, mazeGameSettings);
				}
				else if ((String)mazeTypeComboBox.getSelectedItem()==CONSOLE_MAZE){
					new GameInterface(maze);
				}
				else { //Isto não faz sentido... 
					new ManualMazeGeneratorWindow();
				}
					
			}
		});

		generateNewMazeButton.setBounds(42, 152, 137, 23);
		mazeGameSettings.getContentPane().add(generateNewMazeButton);

	}

	public Maze getMaze(){
		return maze;
	}
}
