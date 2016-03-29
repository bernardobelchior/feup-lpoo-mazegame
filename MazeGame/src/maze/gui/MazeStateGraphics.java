package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Game.GameState;

public class MazeStateGraphics extends JPanel {
	private static final String HERO_WIN_PATH = "res/hero_win.jpg";
	private static final String RUNNING_PATH = "res/hero.png";
	private static final String DRAGON_WIN_PATH = "res/dragon_win.png"; 
	
	private BufferedImage hero_win = null;
	private BufferedImage running = null;
	private BufferedImage dragon_win = null;
	
	private GameState currentGameState;
	
	public MazeStateGraphics() {
		currentGameState = null;
		try {
			hero_win = ImageIO.read(new File(HERO_WIN_PATH));
			dragon_win = ImageIO.read(new File(DRAGON_WIN_PATH));
			running = ImageIO.read(new File(RUNNING_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateState(GameState gameState) {
		if(currentGameState != gameState) {
			currentGameState = gameState;
			super.repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGameState(g);
	}
	
	private void drawGameState(Graphics g) {
		if(currentGameState == null)
			return;
		
		switch (currentGameState) {
		case RUNNING:
			if(running != null)
				g.drawImage(running, 0, 0, null);
			break;
		case HERO_WIN:
			if(hero_win != null)
				g.drawImage(hero_win, 0, 0, null);
			break;
		case DRAGON_WIN:
			if(dragon_win != null)
				g.drawImage(dragon_win, 0, 0, null);
			break;
		}
	}
}
