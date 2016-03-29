package maze.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ManualMazeGeneratorPanel extends JPanel implements MouseListener {

	//Images path
	private static final String WALL_PATH = "res/wall.png";
	private static final String HERO_UNARMED_PATH = "res/hero_unarmed.png";
	private static final String HERO_ARMED_PATH = "res/hero_armed.png";
	private static final String DRAGON_AWAKEN_PATH = "res/dragon_awaken.png";
	private static final String DRAGON_SLEEPING_PATH = "res/dragon_sleeping.png";
	private static final String SWORD_PATH = "res/sword.png";
	private static final String FRAME_PATH = "res/frame.png";

	//Images
	private BufferedImage wall = null;		
	private BufferedImage heroUnarmed = null;
	private BufferedImage heroArmed = null;
	private BufferedImage dragonAwaken = null;
	private BufferedImage dragonSleeping = null;
	private BufferedImage sword = null;
	private BufferedImage frame = null;

	private char[][] maze;
	private int size;
	private ManualMazeGeneration parent;

	public ManualMazeGeneratorPanel(ManualMazeGeneration parent) {
		this.maze = null;
		this.parent = parent;
		addMouseListener(this);
		
		try {
			wall = ImageIO.read(new File(WALL_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			heroUnarmed = ImageIO.read(new File(HERO_UNARMED_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			heroArmed = ImageIO.read(new File(HERO_ARMED_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dragonAwaken = ImageIO.read(new File(DRAGON_AWAKEN_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dragonSleeping = ImageIO.read(new File(DRAGON_SLEEPING_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			sword = ImageIO.read(new File(SWORD_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			frame = ImageIO.read(new File(FRAME_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void generateMaze() {
		generateMaze(size);
	}

	public void generateMaze(int size) {
		this.size = size;
		maze = new char[size][size];
		
		for(int x = 0; x < maze.length; x++) {
			for(int y = 0; y < maze[x].length; y++) {
				maze[y][x] = ' ';
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMaze(g);
	}

	private void drawMaze(Graphics g) {
		if(maze == null)
			return;
		
		for(int x = 0; x < maze.length; x++) {
			for(int y = 0; y < maze[x].length; y++) {
				switch (maze[y][x]) {
				case 'X':
					g.drawImage(wall, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'A':
					g.drawImage(heroArmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'H':
					g.drawImage(heroUnarmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'D':
					g.drawImage(dragonAwaken, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'd':
					g.drawImage(dragonSleeping, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'E':
					g.drawImage(sword, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
					
				default:

					break;
				}
				g.drawImage(frame, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(maze == null)
			return;
		
		switch (parent.getSelectedEntity()) {
		case WALL:
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'X';
			break;
		case HERO_ARMED:
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'A';
			break;
		case HERO_UNARMED:
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'H';
			break;
		case DRAGON_AWAKEN:
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'D';
			break;
		case DRAGON_SLEEPING:
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'd';
			break;
		case SWORD: 
			maze[e.getY()/MazeGraphics.TEXTURE_SIZE][e.getX()/MazeGraphics.TEXTURE_SIZE] = 'E';
			break;
		default:
			break;
		}
		
		 repaint();
	}
}
