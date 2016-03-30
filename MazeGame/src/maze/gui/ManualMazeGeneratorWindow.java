package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;

import maze.logic.Game.EntityType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ManualMazeGeneratorWindow extends JFrame {

	private JPanel contentPane;
	private EntityType selectedEntity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualMazeGeneratorWindow frame = new ManualMazeGeneratorWindow();
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
	public ManualMazeGeneratorWindow() {
		MazeGraphics.loadImages();
		setResizable(false);
		setTitle("Manual Generation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 336);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mazePanel = new MazeDrawingPanel(this);
		mazePanel.setBounds(100, 11, 343, 285);
		contentPane.add(mazePanel);
		
		JPanel elementsPanel = new MazeElementsPanel(this);
		elementsPanel.setBounds(10, 123, 80, ((MazeElementsPanel) elementsPanel).getElementNumber()*MazeGraphics.TEXTURE_SIZE);
		contentPane.add(elementsPanel);
		
		SpinnerNumberModel model = new SpinnerNumberModel(11, 5, 50, 1);
		JSpinner mazeDimensionSpinner = new JSpinner(model);
		mazeDimensionSpinner.setBounds(31, 30, 39, 20);
		contentPane.add(mazeDimensionSpinner);
		
		JLabel mazeDimensionLabel = new JLabel("Dimension:");
		mazeDimensionLabel.setBounds(10, 11, 80, 14);
		contentPane.add(mazeDimensionLabel);
		
		JButton createMazeButton = new JButton("Generate Maze");
		createMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((MazeDrawingPanel) mazePanel).generateMaze(((Integer) mazeDimensionSpinner.getValue()).intValue());
				mazePanel.repaint();
				
				mazePanel.setBounds(mazePanel.getX(), mazePanel.getY(),
						((MazeDrawingPanel) mazePanel).getMazeSize()*MazeGraphics.TEXTURE_SIZE,
						((MazeDrawingPanel) mazePanel).getMazeSize()*MazeGraphics.TEXTURE_SIZE);
				setBounds(0, 0, 
						mazePanel.getX() + mazePanel.getWidth() + 30,
						Math.max(mazePanel.getY() + mazePanel.getHeight() + 50, elementsPanel.getHeight()));
			}
		});
		createMazeButton.setBounds(10, 61, 80, 51);
		contentPane.add(createMazeButton);
		this.selectedEntity = null;
	}
	
	public void setSelectedEntity(EntityType entityType) {
		this.selectedEntity = entityType;
	}
	
	public EntityType getSelectedEntity() {
		return selectedEntity;
	}
}
