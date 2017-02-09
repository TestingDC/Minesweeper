package World;

import java.util.HashMap;
import org.newdawn.slick.Image;

public class TileImport {

	public Image TileSet = null;
	public Image FrameSet = null;
	
	public HashMap<Integer, Image> tileSet = new HashMap<Integer, Image>();
	public HashMap<Integer, Image> frameSet = new HashMap<Integer, Image>();
	
	public TileImport() {
		try {
			TileSet = new Image("src/Resources/tileset.png");
			FrameSet = new Image("src/Resources/outline.png");
			
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
			
			// Outline Frameset
			frameSet.put(0, FrameSet.getSubImage(0 * 4, 0 * 4, 4, 4));
			frameSet.put(1, FrameSet.getSubImage(1 * 4, 0 * 4, 4, 4));
			frameSet.put(2, FrameSet.getSubImage(2 * 4, 0 * 4, 4, 4));
			frameSet.put(3, FrameSet.getSubImage(3 * 4, 0 * 4, 4, 4));
			frameSet.put(4, FrameSet.getSubImage(4 * 4, 0 * 4, 4, 4));
			
			System.out.println("Tiles Loaded into Memory.");
		} catch (Exception e) {
			System.out.println("Error: While Loading TileSets / Tiles: " + e);
		}
	}
}
