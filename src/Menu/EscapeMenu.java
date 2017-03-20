package Menu;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import Core.Minesweeper;
import Core.Minesweeper.GameState;
import World.TileImport;

public class EscapeMenu {

	public static Button mainMenu;
	public boolean win = false;
	public boolean lose = false;
	
	static Font font = new Font("Courier", Font.BOLD, 50);
	static TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
	
	public EscapeMenu() {
		mainMenu = new Button(Display.getWidth()/2, Display.getHeight()/2, 128, 64, TileImport.buttonSet.get(2));
	}
	
	public void render() {
		glColor3f(0.7f, 0.7f, 0.7f); //(rgb)
		glBegin(GL_QUADS);
		glVertex2f(0,0);
		glVertex2f(Display.getWidth() ,0);
		glVertex2f(Display.getWidth(),Display.getHeight());
		glVertex2f(0,Display.getHeight());
		glEnd();
		
		if(win) {
			trueTypeFont.drawString(Display.getWidth()/2 - 105, Display.getHeight()/2 - 200, "You Win", Color.yellow);
		}
		if(lose) {
			trueTypeFont.drawString(Display.getWidth()/2 - 115, Display.getHeight()/2 - 200, "You Lose", Color.red);
		}
		
		mainMenu.render();
	}
	
	public void update() {
		if(mainMenu.wasClicked()){
			Minesweeper.gameState = GameState.MAINMENU;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
	}
	
	public void setWin(boolean bool) {
		win = bool;
	}
	
	public void setLose(boolean bool) {
		lose = bool;
	}
}
