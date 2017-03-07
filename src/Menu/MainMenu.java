package Menu;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import World.TileImport;
public class MainMenu{
	
	public static Button startGame;
	public static Button Options;
	public static Button Quit;
	
public void render(){
	       glColor3f(.9f, .8f, .7f); //(rgb)
	       glBegin(GL_QUADS);
	       glVertex2f(0,0);
	       glVertex2f(Display.getWidth() ,0);
	       glVertex2f(Display.getWidth(),Display.getHeight());
	       glVertex2f(0,Display.getHeight());
	       glEnd();
	       glEnable(GL_TEXTURE_2D);
	       startGame.render();
	       Options.render();
	       Quit.render();
	       glDisable(GL_TEXTURE_2D);
}
public void update(){
	if(startGame.wasClicked()){
		
	}
	if(Options.wasClicked()){
	
		
	}
	if(Quit.wasClicked()){
		System.exit(0);
	}
}
public MainMenu() {
			startGame = new Button(Display.getWidth()/2, Display.getHeight()/2 - 75, 128, 64, TileImport.buttonSet.get(1));
			Options = new Button(Display.getWidth()/2, Display.getHeight()/2 + 25, 128, 64, TileImport.buttonSet.get(3));
			Quit = new Button(Display.getWidth()/2, Display.getHeight()/2 + 125, 128, 64, TileImport.buttonSet.get(5));
	}
}