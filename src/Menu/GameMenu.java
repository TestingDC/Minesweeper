package Menu;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

import Core.Game;
import Core.Game.GameMode;
import Core.Minesweeper;
import Core.Minesweeper.GameState;
import Listen.MouseListener;
import World.TileImport;

public class GameMenu {
		
	public static Button mainMenu;
	public static Button Arcade;
	public static Button Normal;
		
	public GameMenu() {
		mainMenu = new Button(Display.getWidth()/2, Display.getHeight()/2 + 125, 128, 64, TileImport.buttonSet.get(2));
		Arcade = new Button(Display.getWidth()/2, Display.getHeight()/2 + 25, 128, 64, TileImport.buttonSet.get(9));
		Normal = new Button(Display.getWidth()/2, Display.getHeight()/2 - 75, 128, 64, TileImport.buttonSet.get(6));
	}
		
	public void render(){
		glColor3f(0.7f, 0.7f, 0.7f); //(rgb)
		glBegin(GL_QUADS);
		glVertex2f(0,0);
		glVertex2f(Display.getWidth() ,0);
		glVertex2f(Display.getWidth(),Display.getHeight());
		glVertex2f(0,Display.getHeight());
		glEnd();
		mainMenu.render();
		Arcade.render();
		Normal.render();
	}
		
	public void update(){
		if(mainMenu.wasClicked()){
			Minesweeper.gameState = GameState.MAINMENU;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		if(Arcade.wasClicked()){
			Minesweeper.game = new Game(Minesweeper.optionsMenu.width, Minesweeper.optionsMenu.height, Minesweeper.optionsMenu.bombs, GameMode.ARCADE);
			Minesweeper.gameState = GameState.GAME;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
			MouseListener.clear();
		}
		if(Normal.wasClicked()) {
			Minesweeper.game = new Game(Minesweeper.optionsMenu.width, Minesweeper.optionsMenu.height, Minesweeper.optionsMenu.bombs, GameMode.NORMAL);
			Minesweeper.gameState = GameState.GAME;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
			MouseListener.clear();
		}
	}
}