package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import Listen.KeyboardListener;
import Listen.MouseListener;
import Tools.DebugMenu;
import World.TileImport;

public class Minesweeper {

	public enum GameState { MAINMENU, GAMEMENU, GAMEOPTIONS, GAME, PAUSE_STATE }
	public GameState gameState = GameState.MAINMENU;
	
	public int DisplayWidth = 500, DisplayHeight = 500;
	
	public static void main(String Args[]) {
		new Minesweeper();
	}
	
	public Minesweeper() {
		createWindow();
		TileImport.Import();
		gameLoop();
	}
	
	public void gameLoop() {
		Game game = new Game(1);
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			MouseListener.tick();
			KeyboardListener.tick();
			game.update();
			game.render();
	
			if(DebugMenu.debug) {
				DebugMenu.render();
			}
			
			Display.update();
			Display.sync(60);
		}
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