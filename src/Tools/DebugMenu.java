package Tools;

import Core.Minesweeper;

public class DebugMenu {
	
	public static boolean debug = false;
	
	public static boolean toggleXray = false;
	public static boolean toggleCursorLock = false;
	
	public static void render() {
		if(toggleXray) {
			for(int x = 0; x < Minesweeper.TileWidth; x++) {
				for(int y = 0; y < Minesweeper.TileHeight; y++) {
					if(Minesweeper.newLevel.level[x][y].Bomb) {
						Minesweeper.tiles.tileSet.get(99).draw(x * Minesweeper.TileSize + Minesweeper.BoardOffsetX, y * Minesweeper.TileSize + Minesweeper.BoardOffsetY, Minesweeper.TileSize, Minesweeper.TileSize);
					}
				}
			}
		}
		
		
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
			
		}
	}
}
