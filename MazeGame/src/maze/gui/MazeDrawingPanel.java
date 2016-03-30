package maze.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazeDrawingPanel extends JPanel implements MouseListener {
	private char[][] maze;
	private int size;
	private ManualMazeGeneratorWindow parent;

	public MazeDrawingPanel(ManualMazeGeneratorWindow parent) {
		this.maze = null;
		this.parent = parent;
		addMouseListener(this);
		MazeGraphics.loadImages();
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
		
		repaint();
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
					g.drawImage(MazeGraphics.wall, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'A':
					g.drawImage(MazeGraphics.heroArmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'H':
					g.drawImage(MazeGraphics.heroUnarmed, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'D':
					g.drawImage(MazeGraphics.dragonAwaken, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'd':
					g.drawImage(MazeGraphics.dragonSleeping, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				case 'E':
					g.drawImage(MazeGraphics.sword, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
					break;
				default:

					break;
				}
				g.drawImage(MazeGraphics.frame, x*MazeGraphics.TEXTURE_SIZE, y*MazeGraphics.TEXTURE_SIZE, null);
				if(MazeGraphics.frame == null)
					System.out.println("sou nulo");
				System.out.println(MazeGraphics.frame.getHeight());
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
		if(maze == null || parent.getSelectedEntity() == null)
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
	
	public char[][] getMaze() {
		return maze;
	}
}
