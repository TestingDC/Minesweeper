package Menu;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;

import Core.Minesweeper;
import Core.Minesweeper.GameState;
import World.TileImport;

public class OptionsMenu {
	
	public static Button incWidth;
	public static Button decWidth;
	public static Button incHeight;
	public static Button decHeight;
	public static Button incBombs;
	public static Button decBombs;
	public static Button mainMenu;
	public int width = 5;
	public int height = 5;
	public int bombs = 1;
	
	static Font font = new Font("Courier", Font.BOLD, 70);
	static TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
	static Font font2 = new Font("Courier", Font.BOLD, 25);
	static TrueTypeFont trueTypeFont2 = new TrueTypeFont(font2, true);
		
	public OptionsMenu() {
		incWidth = new Button(Display.getWidth()/2 - 100, Display.getHeight()/2 - 75, 50, 50, TileImport.optionsSet.get(0));
		decWidth = new Button(Display.getWidth()/2 - 100, Display.getHeight()/2 + 75, 50, 50, TileImport.optionsSet.get(1));
		incHeight = new Button(Display.getWidth()/2, Display.getHeight()/2 - 75, 50, 50, TileImport.optionsSet.get(0));
		decHeight = new Button(Display.getWidth()/2, Display.getHeight()/2 + 75, 50, 50, TileImport.optionsSet.get(1));
		incBombs = new Button(Display.getWidth()/2 + 100, Display.getHeight()/2 - 75, 50, 50, TileImport.optionsSet.get(0));
		decBombs = new Button(Display.getWidth()/2 + 100, Display.getHeight()/2 + 75, 50, 50, TileImport.optionsSet.get(1));
		mainMenu = new Button(Display.getWidth()/2, Display.getHeight()/2 + 175, 128, 64, TileImport.buttonSet.get(2));
	}

	public void render(){
		glColor3f(0.7f, 0.7f, 0.7f); //(rgb)
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(Display.getWidth(), 0);
		glVertex2f(Display.getWidth(), Display.getHeight());
		glVertex2f(0, Display.getHeight());
		glEnd();
		
		trueTypeFont2.drawString(Display.getWidth()/2 - 100 - 35, Display.getHeight()/2 - 150, "Width");
		trueTypeFont2.drawString(Display.getWidth()/2 - 100 + 55, Display.getHeight()/2 - 150, "Height");
		trueTypeFont2.drawString(Display.getWidth()/2 - 100 + 162, Display.getHeight()/2 - 150, "Bombs");
		
		if(width >= 10) {
			trueTypeFont.drawString(Display.getWidth()/2 - 100 - 40, Display.getHeight()/2 - 50, String.valueOf(width));
		} else {
			trueTypeFont.drawString(Display.getWidth()/2 - 100 - 20, Display.getHeight()/2 - 50, String.valueOf(width));
		}
		if(height >= 10) {
			trueTypeFont.drawString(Display.getWidth()/2 - 40, Display.getHeight()/2 - 50, String.valueOf(height));
		} else {
			trueTypeFont.drawString(Display.getWidth()/2 - 20, Display.getHeight()/2 - 50, String.valueOf(height));
		}
		if(bombs >= 10) {
			trueTypeFont.drawString(Display.getWidth()/2 + 100 - 40, Display.getHeight()/2 - 50, String.valueOf(bombs));
		} else {
			trueTypeFont.drawString(Display.getWidth()/2 + 100 - 20, Display.getHeight()/2 - 50, String.valueOf(bombs));
		}
		mainMenu.render();
		incBombs.render();
		decBombs.render();
		incWidth.render();
		decWidth.render();
		incHeight.render();
		decHeight.render();
	}
	
	public void update(){
		if(mainMenu.wasClicked()) {
			Minesweeper.gameState = GameState.MAINMENU;
			sleep(100);
		}
		if(incWidth.wasClicked()) {
			if(width < 25) {
				width++;
			}
			checkBomb();
			sleep(100);
		}
		if(decWidth.wasClicked()) {
			if(width > 5) {
				width--;
			}
			checkBomb();
			sleep(100);
		}
		if(incHeight.wasClicked()) {
			if(height < 25) {
				height++;
			}
			checkBomb();
			sleep(100);
		}
		if(decHeight.wasClicked()) {
			if(height > 5) {
				height --;
			}
			checkBomb();
			sleep(100);
		}
		if(incBombs.wasClicked()) {
			if(bombs < (width*height)-1) {
				bombs++;
			}
			sleep(100);
		}
		if(decBombs.wasClicked()) {
			if(bombs > 1) {
				bombs--;
			}
			sleep(100);
		}
	}
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {}
	}
	
	private void checkBomb() {
		if((width*height) < bombs) {
			bombs = width*height - 1;
		}
	}
}