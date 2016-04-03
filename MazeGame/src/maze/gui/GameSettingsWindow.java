package maze.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

import maze.cli.GameInterface;
import maze.gui.MazeGraphics.DisplayMode;
import maze.logic.Game.GameMode;
import maze.logic.Maze;
import maze.logic.RandomMazeGenerator;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;


public class GameSettingsWindow {
	private static final String STATIONARY_DRAGON_TEXT = "Stationary";
	private static final String RANDOM_DRAGON_TEXT = "Random";
	private static final String SLEEPING_DRAGON_TEXT = "Sleeping";
	private static final String CONSOLE_MAZE = "Console";
	private static final String TEXT_MAZE = "Text";
	private static final String GRAPHICAL_MAZE = "Graphical";

	public static GameSettingsWindow mazeWindow;

	private JFrame gameSettingsFrame;
	private JSpinner mazeDimensionSpinner;
	private JSpinner dragonNumberSpinner;
	private JComboBox<String> gameModeComboBox;
	private JComboBox<String> mazeTypeComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mazeWindow = new GameSettingsWindow();
					mazeWindow.gameSettingsFrame.setVisible(true);
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

		MazeGraphics.centerFrame(gameSettingsFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MazeGraphics.loadImages();
		gameSettingsFrame = new JFrame();
		gameSettingsFrame.setResizable(false);
		gameSettingsFrame.setTitle("Game Settings");
		gameSettingsFrame.setBounds(100, 100, 285, 269);
		gameSettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameSettingsFrame.getContentPane().setLayout(null);

		JLabel mazeDimensionLabel = new JLabel("Maze Dimension:");
		mazeDimensionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		mazeDimensionLabel.setBounds(10, 10, 123, 20);
		gameSettingsFrame.getContentPane().add(mazeDimensionLabel);

		JLabel dragonNumberLabel = new JLabel("Number of Dragons:");
		dragonNumberLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		dragonNumberLabel.setBounds(10, 41, 123, 20);
		gameSettingsFrame.getContentPane().add(dragonNumberLabel);

		JLabel dragonModeLabel = new JLabel("Dragon Mode:");
		dragonModeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		dragonModeLabel.setBounds(10, 73, 123, 14);
		gameSettingsFrame.getContentPane().add(dragonModeLabel);

		gameModeComboBox = new JComboBox<String>();
		gameModeComboBox.setBounds(143, 70, 90, 20);
		gameSettingsFrame.getContentPane().add(gameModeComboBox);
		gameModeComboBox.addItem(STATIONARY_DRAGON_TEXT);
		gameModeComboBox.addItem(RANDOM_DRAGON_TEXT);
		gameModeComboBox.addItem(SLEEPING_DRAGON_TEXT);

		JLabel mazeTypeLabel = new JLabel("Maze Type:");
		mazeTypeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		mazeTypeLabel.setBounds(10, 104, 123, 14);
		gameSettingsFrame.getContentPane().add(mazeTypeLabel);

		mazeTypeComboBox = new JComboBox<String>();
		mazeTypeComboBox.setBounds(143, 101, 90, 20);
		gameSettingsFrame.getContentPane().add(mazeTypeComboBox);
		mazeTypeComboBox.addItem(GRAPHICAL_MAZE);
		mazeTypeComboBox.addItem(TEXT_MAZE);
		mazeTypeComboBox.addItem(CONSOLE_MAZE);

		SpinnerNumberModel model = new SpinnerNumberModel(11, 5, 50, 1);
		mazeDimensionSpinner = new JSpinner(model);
		mazeDimensionSpinner.setBounds(143, 11, 50, 20);
		gameSettingsFrame.getContentPane().add(mazeDimensionSpinner);

		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 10, 1);
		dragonNumberSpinner = new JSpinner(model1);
		dragonNumberSpinner.setBounds(143, 42, 50, 20);
		gameSettingsFrame.getContentPane().add(dragonNumberSpinner);

		JButton createManualMazeButton = new JButton("Create Maze Manually");
		createManualMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ManualMazeGeneratorWindow(getDisplayMode((String)mazeTypeComboBox.getSelectedItem()),
						getGameMode((String) gameModeComboBox.getSelectedItem()));
			}
		});
		createManualMazeButton.setBounds(40, 186, 200, 35);
		gameSettingsFrame.getContentPane().add(createManualMazeButton);

		JButton generateMazeButton = new JButton("Generate Maze");
		generateMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RandomMazeGenerator rmg = new RandomMazeGenerator(
						((Integer) mazeDimensionSpinner.getValue()).intValue(),
						((Integer) dragonNumberSpinner.getValue()).intValue());

				Maze maze = new Maze(rmg.getMaze(), getGameMode((String) gameModeComboBox.getSelectedItem()));

				switch(getDisplayMode((String)mazeTypeComboBox.getSelectedItem())) {
				case GRAPHICAL:
					new GraphicalGameWindow(maze);
					break;
				case TEXTUAL:
					new TextualGameWindow(maze);
					break;
				case CONSOLE: 
					new GameInterface(maze);
					break;
				}
			}
		});
		generateMazeButton.setBounds(40, 140, 200, 35);
		gameSettingsFrame.getContentPane().add(generateMazeButton);

	}

	private DisplayMode getDisplayMode(String displayMode) {
		if (displayMode == GRAPHICAL_MAZE){
			return DisplayMode.GRAPHICAL;
		} else if(displayMode == TEXT_MAZE){
			return DisplayMode.TEXTUAL;
		} else if (displayMode == CONSOLE_MAZE){
			return DisplayMode.CONSOLE;
		}

		return DisplayMode.GRAPHICAL;
	}

	private GameMode getGameMode(String gameMode) {
		switch (gameMode){
		case GameSettingsWindow.STATIONARY_DRAGON_TEXT :
			return GameMode.STATIONARY;
		case GameSettingsWindow.RANDOM_DRAGON_TEXT :
			return GameMode.RANDOM_MOVEMENT;
		case GameSettingsWindow.SLEEPING_DRAGON_TEXT :
			return GameMode.SLEEP_RANDOM_MOVEMENT;
		default:
			return GameMode.STATIONARY;
		}
	}
}
