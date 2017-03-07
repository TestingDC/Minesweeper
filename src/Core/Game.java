package Core;

import org.lwjgl.opengl.Display;

import Listen.MouseListener;
import Menu.Button;
import World.Level;
import World.Tile;
import World.TileImport;

public class Game {

	public int width, height = 0;
	public static int TileSize = 16; // 16
	public static int frameThickness = 8; // 8
	public static int boardOffsetX = 8, boardOffsetY = 46; // 4 , 36
	public Level level;
	public static Button button;
	
	public boolean gameOver = false;
	
	public enum GameMode { NORMAL, MYSTERY, ARCADE, TIMEATTACK}
	public GameMode gameMode = GameMode.NORMAL;
	
	public Game(int width, int height, int numberOfBombs, GameMode mode) {
		this.width = width;
		this.height = height;
		this.gameMode = mode;
		this.level = new Level(width, height);
		level.generateGame(numberOfBombs, mode);
		
		if((width * TileSize + frameThickness * 2) > Display.getWidth() || (height * TileSize + frameThickness * 2) > Display.getHeight()) {
			System.out.println("ERROR: Board width or height is larger than Display Size.");
			System.out.println("TileMap (In Tiles): " + width + "x" + height + " (In Pixels): " + width * TileSize + "x" + height * TileSize);
			System.out.println("DisplaySize (In Pixels): " + Display.getWidth() + "x" + Display.getHeight());
			System.out.println("FrameThickness (In Pixels): " + frameThickness + " TileSize (In Pixels): " + TileSize);
			System.exit(0);
			return;
		}
		
		// Calculate Board Offset.
		boardOffsetX = (Display.getWidth()/2) - ((width * TileSize) / 2) - 2;
		boardOffsetY = (Display.getHeight()/2) - ((height * TileSize) / 2);
		
		int frameThickness = 8; // 8
		int frameOffsetY = Game.boardOffsetY - frameThickness;
		int frameWidth = (frameThickness * 2) + (level.level.length * TileSize);
		
		button = new Button((frameWidth/2)  + (Game.boardOffsetX - frameThickness), frameOffsetY - 12, 24, 24, TileImport.tileSet.get(95));
	}
	
	public void update() {
		gameOver = level.getGameOver();
		
		if(!gameOver) {
			if(MouseListener.clicked) {
				MouseListener.clicked = false;
				level.clickBoard((int) Math.floor(MouseListener.MouseX / TileSize), (int) Math.floor(MouseListener.MouseY / TileSize));
				
				if(button.wasClicked()) {
					int temp = level.TotalBombs;
					level = new Level(width,height);
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
		
		if(level.checkWin()) {
			System.out.println("GG");
		}
	}
	
	public void render() {
		level.render();
		renderFrame();
		button.render();
	}
	
	public void renderFrame() {
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
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public Tile getTile(int x, int y) {
		return level.level[x][y];
	}
}
