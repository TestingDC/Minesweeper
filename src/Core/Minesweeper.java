package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import Listen.KeyboardListener;
import Listen.MouseListener;
import Tools.DebugMenu;
import World.Level;
import World.TileImport;

public class Minesweeper {

	public int DisplayWidth = 500, DisplayHeight = 500;
	public static int TileWidth = 20, TileHeight = 20;
	public static int TileSize = 16;
	
	public static int BoardOffsetX = 4, BoardOffsetY = 36;
	
	public static boolean done = false;
	
	public static TileImport tiles;
	public static Level newLevel;
	
	public static void main(String Args[]) {
		new Minesweeper(); // Create a Instance of Minesweeper.
	}
	
	// Constructor - Loads things whenever new object instance is created.
	public Minesweeper() {
		createWindow(); // Create Window
		tiles = new TileImport(); // Load TileSets into Memory
		gameLoop(); // Start GameLoop
	}
	
	public void gameLoop() {

		newLevel = new Level();
		newLevel.generateBombs(75);
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			// Game Logic + Click Logic
			if(!done) {
				if(MouseListener.clicked) {
					MouseListener.clicked = false;
					newLevel.updateBoard((int) Math.floor(MouseListener.MouseX / TileSize), (int) Math.floor(MouseListener.MouseY / TileSize));
					newLevel.checkWin();
				}
				if(MouseListener.rightClicked) {
					MouseListener.rightClicked = false;
					int tempX = (int) Math.floor(MouseListener.MouseX / TileSize);
					int tempY = (int) Math.floor(MouseListener.MouseY / TileSize);
					newLevel.markTile(tempX, tempY);
				}
			}
			// Done Updating Board
			
			MouseListener.tick();
			KeyboardListener.tick();
			newLevel.render();
			if(DebugMenu.debug) {
				DebugMenu.render();
			}
			renderFrame();
			
			Display.update();
			Display.sync(60);
		}
	}
	
	// Renders a Nice Frame around Minesweeper
	public void renderFrame() {
		int frameOffsetX = 0;
		int frameOffsetY = 32;
		int frameThickness = 4;
		int frameWidth = frameThickness * ((TileWidth * TileSize) / frameThickness);
		int frameHeight = frameThickness * ((TileHeight * TileSize) / frameThickness);
		
		// Draw Edges
		tiles.frameSet.get(0).draw(frameOffsetX, frameThickness + frameOffsetY, frameThickness, frameHeight);
		tiles.frameSet.get(0).draw(frameWidth+frameThickness+frameOffsetX, frameThickness + frameOffsetY, frameThickness, frameHeight);
		tiles.frameSet.get(0).draw(frameOffsetX, frameOffsetY, frameWidth+frameThickness, frameThickness);
		tiles.frameSet.get(0).draw(frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameWidth+frameThickness, frameThickness);
		
		// Draw Corners
		tiles.frameSet.get(1).draw(frameOffsetX, frameOffsetY, frameThickness, frameThickness);
		tiles.frameSet.get(2).draw(frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameThickness, frameThickness);
		tiles.frameSet.get(3).draw(frameWidth+frameThickness+frameOffsetX, frameOffsetY, frameThickness, frameThickness);
		tiles.frameSet.get(4).draw(frameWidth+frameThickness+frameOffsetX, frameHeight+frameThickness+frameOffsetY, frameThickness, frameThickness);
	}
	
	// Creates window
	public void createWindow() {
		try {
			DisplayMode displayMode = new DisplayMode(DisplayWidth, DisplayHeight);
			Display.setDisplayMode(displayMode);
	    	Display.create(new PixelFormat(0, 16, 1));
	    	Display.setResizable(false);
	    	Display.setTitle("Minesweeper");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	  	  glEnable(GL_TEXTURE_2D);
		  glDisable(GL_DEPTH_TEST);
	      glMatrixMode(GL_PROJECTION);
	      glLoadIdentity();
	      glOrtho(0, DisplayWidth, DisplayHeight, 0, -1, 1);
	      glMatrixMode(GL_MODELVIEW);
	      glEnable(GL_BLEND);
	      glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	  	  glEnable(GL_COLOR_MATERIAL);
	}
}