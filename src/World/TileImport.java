package World;

import java.util.HashMap;
import org.newdawn.slick.Image;

public class TileImport {

	public static Image TileSet = null;
	public static Image FrameSet = null;
	public static Image ButtonSet = null;
	public static Image OptionsSet = null;
	public static Image cursor = null;
	
	public static HashMap<Integer, Image> tileSet = new HashMap<Integer, Image>();
	public static HashMap<Integer, Image> frameSet = new HashMap<Integer, Image>();
	public static HashMap<Integer, Image> buttonSet = new HashMap<Integer, Image>();
	public static HashMap<Integer, Image> optionsSet = new HashMap<Integer, Image>();
	
	public static void Import() {
		try {
			TileSet = new Image("Resources/tileset.png");
			FrameSet = new Image("Resources/outline.png");
			ButtonSet = new Image("Resources/buttons.png");
			OptionsSet = new Image("Resources/options.png");
			cursor = new Image("Resources/cursor.png");
			
			// Map Tiles
			tileSet.put(-1, TileSet.getSubImage(10 * 16, 0, 16, 16)); // Untapped Tile
			tileSet.put(0, TileSet.getSubImage(8 * 16, 0, 16, 16)); // Tapped Tile
			
			// Number Tiles
			tileSet.put(1, TileSet.getSubImage(0 * 16, 0, 16, 16)); // 1
			tileSet.put(2, TileSet.getSubImage(1 * 16, 0, 16, 16)); // 2
			tileSet.put(3, TileSet.getSubImage(2 * 16, 0, 16, 16)); // 3
			tileSet.put(4, TileSet.getSubImage(3 * 16, 0, 16, 16)); // 4
			tileSet.put(5, TileSet.getSubImage(4 * 16, 0, 16, 16)); // 5
			tileSet.put(6, TileSet.getSubImage(5 * 16, 0, 16, 16)); // 6
			tileSet.put(7, TileSet.getSubImage(6 * 16, 0, 16, 16)); // 7
			tileSet.put(8, TileSet.getSubImage(7 * 16, 0, 16, 16)); // 8
			
			// Special Tiles - Bombs, Marks, Etc..
			tileSet.put(100, TileSet.getSubImage(9 * 16, 0, 16, 16)); // Bomb Tile
			tileSet.put(99, TileSet.getSubImage(2 * 16, 1 * 16, 16, 16)); // Bomb (Red) Tile
			tileSet.put(98, TileSet.getSubImage(3 * 16, 1 * 16, 16, 16)); // Bomb (Crossed-Out) Tile
			tileSet.put(97, TileSet.getSubImage(0 * 16, 1 * 16, 16, 16)); // Marked Flag Tile
			tileSet.put(96, TileSet.getSubImage(1 * 16, 1 * 16, 16, 16)); // Question Mark Tile
			tileSet.put(95, TileSet.getSubImage(10 * 16, 1 * 16, 16, 16)); // Star Tile
			tileSet.put(94, TileSet.getSubImage(4 * 16, 1 * 16, 16, 16)); // BullsEye Tile
			tileSet.put(93, TileSet.getSubImage(5 * 16, 1 * 16, 16, 16)); // Sonar Tile
			tileSet.put(92, TileSet.getSubImage(6 * 16, 1 * 16, 16, 16)); // Roulette Tile
			tileSet.put(91, TileSet.getSubImage(7 * 16, 1 * 16, 16, 16)); // BombSpread Tile
			tileSet.put(90, TileSet.getSubImage(8 * 16, 1 * 16, 16, 16)); // Amnesia Tile
			
			// Outline Frameset
			frameSet.put(0, FrameSet.getSubImage(0 * 4, 0 * 4, 4, 4));
			frameSet.put(1, FrameSet.getSubImage(1 * 4, 0 * 4, 4, 4));
			frameSet.put(2, FrameSet.getSubImage(2 * 4, 0 * 4, 4, 4));
			frameSet.put(3, FrameSet.getSubImage(3 * 4, 0 * 4, 4, 4));
			frameSet.put(4, FrameSet.getSubImage(4 * 4, 0 * 4, 4, 4));
			
			//Buttons
			buttonSet.put(1, ButtonSet.getSubImage(0 * 128, 0 * 64, 128, 64)); //Start Game
			buttonSet.put(2, ButtonSet.getSubImage(1 * 128, 0 * 64, 128, 64)); //Main Menu
			buttonSet.put(3, ButtonSet.getSubImage(2 * 128, 0 * 64, 128, 64)); //Options
			buttonSet.put(4, ButtonSet.getSubImage(3 * 128, 0 * 64, 128, 64)); //Restart
			buttonSet.put(5, ButtonSet.getSubImage(4 * 128, 0 * 64, 128, 64)); //Quit
			buttonSet.put(6, ButtonSet.getSubImage(5 * 128, 0 * 64, 128, 64)); //Classic
			buttonSet.put(7, ButtonSet.getSubImage(6 * 128, 0 * 64, 128, 64)); //Mystery
			buttonSet.put(8, ButtonSet.getSubImage(7 * 128, 0 * 64, 128, 64)); //Time Attack
			buttonSet.put(9, ButtonSet.getSubImage(8 * 128, 0 * 64, 128, 64)); //Arcade
			optionsSet.put(0, OptionsSet.getSubImage(0 * 50, 0 * 50, 50, 50)); // Up Arrow
			optionsSet.put(1, OptionsSet.getSubImage(1 * 50, 0 * 50, 50, 50)); // Down Arrow
			
			
			System.out.println("Tiles Loaded into Memory.");
		} catch (Exception e) {
			System.out.println("Error: While Loading TileSets / Tiles: " + e);
		}
	}
}
