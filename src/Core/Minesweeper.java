package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import Listen.KeyboardListener;
import Listen.MouseListener;
import Menu.GameMenu;
import Menu.MainMenu;
import Menu.OptionsMenu;
import Tools.DebugMenu;
import World.TileImport;

public class Minesweeper {

	public enum GameState { MAINMENU, GAMEMENU, OPTIONS, GAME, PAUSE}
	public static GameState gameState = GameState.MAINMENU;
	
	public int DisplayWidth = 500, DisplayHeight = 500;
	
	public static Game game;
	public static MainMenu menu;
	public static GameMenu gameMenu;
	public static OptionsMenu optionsMenu;
	
	public static void main(String Args[]) {
		new Minesweeper();
	}
	
	public Minesweeper() {
		createWindow();
		TileImport.Import();
		Mouse.setGrabbed(true);
		menu = new MainMenu();
		gameMenu = new GameMenu();
		optionsMenu = new OptionsMenu();
		gameLoop();
	}
	
	public void gameLoop() {
		while(!Display.isCloseRequested()) {
			MouseListener.tick();
			KeyboardListener.tick();

			renderUpdate();

			Display.update();
			Display.sync(60);
		}
	}
	
	public static void renderUpdate() {
		glClear(GL_COLOR_BUFFER_BIT);
		glDisable(GL_TEXTURE_2D);
		switch(gameState){
			case MAINMENU:
				menu.render();
				menu.update();
				break;
			case GAME:
				game.update();
				game.render();
				break;
			case PAUSE:
				break;
			case GAMEMENU:
				gameMenu.render();
				gameMenu.update();
				break;
			case OPTIONS:
				optionsMenu.render();
				optionsMenu.update();
				break;
		}
		TileImport.cursor.draw(Mouse.getX(), Math.abs(Mouse.getY() - Display.getHeight()), 8, 8);
		
		if(DebugMenu.debug) {
			DebugMenu.render();
		}
		glEnable(GL_TEXTURE_2D);
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