package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import Core.Game.GameMode;
import Listen.KeyboardListener;
import Listen.MouseListener;
import Tools.DebugMenu;
import World.TileImport;

public class Minesweeper {

	public enum GameState { MAINMENU, GAMEMENU, GAMEOPTIONS, GAME, PAUSE_STATE }
	public GameState gameState = GameState.MAINMENU;
	
	public int DisplayWidth = 500, DisplayHeight = 500;
	
	public static Clock clock;
	public static Game game;
	
	public static void main(String Args[]) {
		new Minesweeper();
	}
	
	public Minesweeper() {
		createWindow();
		TileImport.Import();
		gameLoop();
	}
	
	public void gameLoop() {
		clock = new Clock();
		clock.start();
		
		game = new Game(20, 20, 50, GameMode.ARCADE); // (int) MapWidth, (int) MapHeight, (int) NumberOfBombs, (GameMode) gameMode
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);

			MouseListener.tick();
			KeyboardListener.tick();
			game.render();
	
			if(DebugMenu.debug) {
				DebugMenu.render();
			}
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		clock.interrupt();
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