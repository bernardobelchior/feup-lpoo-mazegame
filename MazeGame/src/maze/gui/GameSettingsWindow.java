package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import maze.cli.GameInterface;
import maze.logic.Game.GameMode;
import maze.logic.Maze;
import maze.logic.RandomMazeGenerator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//TODO por esta janela a fechar e abrir as outras automaticamente
//TODO mudar textbox de dimensaodo labirinto para combobox com limite de 50 e minimo de ? ver a outra

public class GameSettingsWindow {
	private static final String STATIONARY_DRAGON_TEXT = "Stationary";
	private static final String RANDOM_DRAGON_TEXT = "Random";
	private static final String SLEEPING_DRAGON_TEXT = "Sleeping";
	private static final String CONSOLE_MAZE = "Console Maze";
	private static final String TEXT_MAZE = "Text Maze";
	private static final String GRAPHICAL_MAZE = "Graphical Maze";

	private JFrame mazeGameSettings;
	private JTextField mazeDimensionTextField;
	private JTextField dragonNumberTextField;
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
		mazeTypeComboBox.addItem(GRAPHICAL_MAZE);
		mazeTypeComboBox.addItem(TEXT_MAZE);
		mazeTypeComboBox.addItem(CONSOLE_MAZE);

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

				maze = new Maze(rmg.getMaze(), gameMode);
			
				if ((String)mazeTypeComboBox.getSelectedItem() == GRAPHICAL_MAZE)
					new GraphicalGameWindow(maze);
				else if((String)mazeTypeComboBox.getSelectedItem() == TEXT_MAZE)
					new TextualGameWindow(maze);
				else
					new GameInterface(maze);
					
				
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
			}
		});

		generateNewMazeButton.setBounds(42, 152, 137, 23);
		mazeGameSettings.getContentPane().add(generateNewMazeButton);

	}


	public Maze getMaze(){
		return maze;
	}
}
