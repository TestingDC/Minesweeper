package Core;

import Listen.MouseListener;
import Menu.Button;
import World.Level;
import World.TileImport;

public class Game {

	public static int TileSize = 16; // 16
	public static int boardOffsetX = 8, boardOffsetY = 46; // 4 , 36
	public static Level level = new Level(20, 20);
	public static Button button;
	
	public static boolean gameOver = false;
	
	public Game(int numberOfBombs) {
		level.generateBombs(numberOfBombs);
	}
	
	public void update() {
		gameOver = level.getGameOver();
		
		int frameThickness = 8; // 8
		int frameOffsetY = Game.boardOffsetY - frameThickness;
		int frameWidth = (frameThickness * 2) + (level.level.length * TileSize);
		button = new Button((frameWidth/2), frameOffsetY - 12, 24, 24, TileImport.tileSet.get(95));
		
		if(!gameOver) {
			if(MouseListener.clicked) {
				MouseListener.clicked = false;
				level.updateBoard((int) Math.floor(MouseListener.MouseX / TileSize), (int) Math.floor(MouseListener.MouseY / TileSize));
				level.checkWin();
				
				if(button.wasClicked()) {
					int temp = level.TotalBombs;
					level = new Level(20,20);
					level.generateBombs(temp);
				}
			}
			if(MouseListener.rightClicked) {
				MouseListener.rightClicked = false;
				int tempX = (int) Math.floor(MouseListener.MouseX / TileSize);
				int tempY = (int) Math.floor(MouseListener.MouseY / TileSize);
				level.markTile(tempX, tempY);
			}
		}
	}
	
	public void render() {
		level.render();
		renderFrame();
		button.render();
	}
	
	public void renderFrame() {
		int frameThickness = 8; // 8
		int frameOffsetX = Game.boardOffsetX - frameThickness;
		int frameOffsetY = Game.boardOffsetY - frameThickness;
		int frameWidth = frameThickness * ((level.level.length * TileSize) / frameThickness);
		int frameHeight = frameThickness * ((level.level.length * TileSize) / frameThickness);
		
		// Draw Edges
		TileImport.frameSet.get(0).draw(frameOffsetX, frameThickness + frameOffsetY, frameThickness, frameHeight);
		TileImport.frameSet.get(0).draw(frameWidth+frameThickness+frameOffsetX, frameThickness + frameOffsetY, frameThickness, frameHeight);
		TileImport.frameSet.get(0).draw(frameOffsetX, frameOffsetY, frameWidth+frameThickness, frameThickness);
		TileImport.frameSet.get(0).draw(frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameWidth+frameThickness, frameThickness);
		
		// Draw Corners
		TileImport.frameSet.get(1).draw(frameOffsetX, frameOffsetY, frameThickness, frameThickness);
		TileImport.frameSet.get(2).draw(frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameThickness, frameThickness);
		TileImport.frameSet.get(3).draw(frameWidth+frameThickness+frameOffsetX, frameOffsetY, frameThickness, frameThickness);
		TileImport.frameSet.get(4).draw(frameWidth+frameThickness+frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameThickness, frameThickness);
		
		//Top Bar
		
	}
}
