package Tools;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import Core.Game;
import World.TileImport;

public class DebugMenu {
	
	public static boolean debug = false;
	
	public static boolean toggleXray = false;
	public static boolean toggleCursorLock = false;
	
	static Font font = new Font("Courier", Font.BOLD, 12);
	static TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
	
	public static void render() {
		if(toggleXray) {
			for(int x = 0; x < Game.level.level.length; x++) {
				for(int y = 0; y < Game.level.level[0].length; y++) {
					if(Game.level.level[x][y].Bomb) {
						TileImport.tileSet.get(99).draw(x * Game.TileSize + Game.boardOffsetX, y * Game.TileSize + Game.boardOffsetY, Game.TileSize, Game.TileSize);
					}
				}
			}
			trueTypeFont.drawString(36, 28, "On", Color.green);
		} else {
			trueTypeFont.drawString(36, 28, "Off", Color.yellow);
		}
		
		if(toggleCursorLock) {
			
		}
		
		trueTypeFont.drawString(0, 0, "Display: " + Display.getWidth() + " x " + Display.getHeight(), Color.red);
		trueTypeFont.drawString(0, 14, "Board: " + Game.level.level.length + " x " + Game.level.level[0].length + " Offset: " + Game.boardOffsetX + " x " + Game.boardOffsetY, Color.red);
		trueTypeFont.drawString(0, 28, "Xray: ", Color.red);
	}
	
	public void toggleXray() {
		if(!toggleXray) {
			toggleXray = true;
		} else {
			toggleXray = false;
		}
	}
	
	public void toggleCursorLock() {
		if(!toggleCursorLock) {
			toggleCursorLock = true;
		} else {
			toggleCursorLock = false;
		}
	}
}
