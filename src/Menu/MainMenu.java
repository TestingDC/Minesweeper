package Menu;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import Core.Minesweeper;
import Core.Minesweeper.GameState;
import World.TileImport;
public class MainMenu{
	
	public static Button startGame;
	public static Button Options;
	public static Button Quit;
	
	public MainMenu() {
		startGame = new Button(Display.getWidth()/2, Display.getHeight()/2 - 75, 128, 64, TileImport.buttonSet.get(1));
		Options = new Button(Display.getWidth()/2, Display.getHeight()/2 + 25, 128, 64, TileImport.buttonSet.get(3));
		Quit = new Button(Display.getWidth()/2, Display.getHeight()/2 + 125, 128, 64, TileImport.buttonSet.get(5));
	}
	
	public void render(){
		glColor3f(0.7f, 0.7f, 0.7f); //(rgb)
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(Display.getWidth(), 0);
		glVertex2f(Display.getWidth(), Display.getHeight());
		glVertex2f(0, Display.getHeight());
		glEnd();
		startGame.render();
		Options.render();
		Quit.render();
	}
	
	public void update(){
		if(startGame.wasClicked()){
			Minesweeper.gameState = GameState.GAMEMENU;
			try { 
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		if(Options.wasClicked()){
			Minesweeper.gameState = GameState.OPTIONS;
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		if(Quit.wasClicked()){
			System.exit(0);
		}
	}
}