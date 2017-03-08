package Menu;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

import Core.Minesweeper;
import Core.Minesweeper.GameState;
import Listen.MouseListener;
import World.TileImport;

public class GameMenu {
		
		public static Button mainMenu;
		public static Button Arcade;
		
	public void render(){
		       glColor3f(.9f, .8f, .7f); //(rgb)
		       glBegin(GL_QUADS);
		       glVertex2f(0,0);
		       glVertex2f(Display.getWidth() ,0);
		       glVertex2f(Display.getWidth(),Display.getHeight());
		       glVertex2f(0,Display.getHeight());
		       glEnd();
		       glEnable(GL_TEXTURE_2D);
		       mainMenu.render();
		       Arcade.render();
		       glDisable(GL_TEXTURE_2D);
	}
	public void update(){
		if(mainMenu.wasClicked()){
		Minesweeper.gameState = GameState.MAINMENU;
		try {
			Thread.sleep(25);
		} catch (Exception e) {
		}
	}
		if(Arcade.wasClicked()){
			Minesweeper.gameState = GameState.GAME;
			try {
				Thread.sleep(205);
			} catch (Exception e) {
			}
			MouseListener.clicked = false;
		}
}
	public GameMenu() {
				mainMenu = new Button(Display.getWidth()/2, Display.getHeight()/2 + 125, 128, 64, TileImport.buttonSet.get(2));
				Arcade = new Button(Display.getWidth()/2, Display.getHeight()/2 + 25, 128, 64, TileImport.buttonSet.get(9));
	}
}
