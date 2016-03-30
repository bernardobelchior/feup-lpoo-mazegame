package maze.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import maze.logic.Game.GameState;

@SuppressWarnings("serial")
public class MazeStateDisplayPanel extends JPanel {
	private GameState currentGameState;
	
	public MazeStateDisplayPanel() {
		currentGameState = null;
	}
	
	public void updateState(GameState gameState) {
		if(currentGameState != gameState) {
			currentGameState = gameState;
			repaint();
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
			if(MazeGraphics.gameRunning != null)
				g.drawImage(MazeGraphics.gameRunning, 0, 0, null);
			break;
		case HERO_WIN:
			if(MazeGraphics.heroArmed != null)
				g.drawImage(MazeGraphics.heroArmed, 0, 0, null);
			break;
		case DRAGON_WIN:
			if(MazeGraphics.dragonAwaken != null)
				g.drawImage(MazeGraphics.dragonAwaken, 0, 0, null);
			break;
		}
	}
}
