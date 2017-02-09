package World;

import java.util.Random;

import Core.Minesweeper;

public class Level {

	public Tile[][] level = new Tile[Minesweeper.TileWidth][Minesweeper.TileHeight];
	public int TotalBombs = 0;
	
	public Level() {
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				level[x][y] = new Tile(-1, false, false, false, false);
			}
		}
	}
	
	/*
	 * Using a simple render method to render since preformance isn't much of a problem in this small game.
	 * 
	 * Side Note: If the game was more complex and included hundreds of textures then use spritebatching to sort and call render to GPU once
	 * instead of switching between textures per Tile along the axes.
	 */
	public void render() {
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				level[x][y].render(x, y);
			}
		}
	}
	
	public void checkWin() {
		if(getUnmarkedTotalBombs() == 0 && getTotalMarks() == TotalBombs) { // Take this out of cursor update and put into main tick.
			for(int x = 0; x < level.length; x++) {
				for(int y = 0; y < level[0].length; y++) {
					if(level[x][y].Number == -1) {
						level[x][y].Number = getNearbyBombs(x, y);
						level[x][y].Checked = true;
					}
				}
			}
			Minesweeper.done = true;
		}
	}
	
	public void updateBoard(int cx, int cy) {
		if(cx >= Minesweeper.TileWidth || cx < 0 || cy >= Minesweeper.TileHeight || cy < 0) {
			return;
		}
		
		// Click Logic
		if(level[cx][cy].Bomb == true && level[cx][cy].Marked == false) {
			for(int x = 0; x < level.length; x++) {
				for(int y = 0; y < level[0].length; y++) {
					if(level[x][y].Bomb && (level[x][y].Marked == false)) {
						level[x][y].Number = 100;
					}
					if(!(level[x][y].Bomb) && (level[x][y].Marked == true)) {
						level[x][y].Number = 98;
					}
				}
			}
			level[cx][cy].Number = 99;
			Minesweeper.done = true;
			return;
		}
		rescursiveCheck(cx, cy);
	}
	
	public void rescursiveCheck(int x, int y) {
		if((level[x][y].Number == -1) && (level[x][y].Checked == false)) {
			if(getNearbyBombs(x, y) == 0) {
				level[x][y].Number = 0;
				level[x][y].Checked = true;
			try {
				rescursiveCheck(x - 1, y - 1);
				rescursiveCheck(x, y - 1);
				rescursiveCheck(x + 1, y - 1);
				rescursiveCheck(x - 1, y);
				rescursiveCheck(x, y);
				rescursiveCheck(x + 1, y);
				rescursiveCheck(x - 1, y + 1);
				rescursiveCheck(x, y + 1);
				rescursiveCheck(x + 1, y + 1);
				} catch(Exception e) {
				
				}
			} else {
				level[x][y].Number = getNearbyBombs(x, y);
				level[x][y].Checked = true;
			}
		}
	}
	
	public int getNearbyBombs(int x, int y) {
		int BombAmount = 0;
		for(int xi = (x - 1); xi < (x + 2); xi++) {
			for(int yi = (y - 1); yi < (y + 2); yi++) {
				if(xi < 0 || xi >= Minesweeper.TileWidth || yi < 0 || yi >= Minesweeper.TileHeight) {
					// Out of Bounds, Do Nothing
				} else {
					if(level[xi][yi].Bomb == true) {
						BombAmount++;
					}
				}
			}
		}
		return BombAmount;
	}
	
	public int getUnmarkedTotalBombs() {
		int BombCount = 0;
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				if(level[x][y].Bomb && !(level[x][y].Marked)) {
					BombCount++;
				}
			}
		}
		return BombCount;
	}
	
	public int getTotalMarks() {
		int TotalMarks = 0;
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				if(level[x][y].Marked == true) {
					TotalMarks++;
				}
			}
		}
		return TotalMarks;
	}
	
	// Randomly Generates Bombs according to the number of bombs requested.
	public void generateBombs(int numberOfBombs) {
		if(numberOfBombs >= (Minesweeper.TileWidth * Minesweeper.TileHeight)) {
			System.out.println("Error: Number of Bombs must be less than the total tiles.");
			return;
		} else {
			Random rand = new Random();
			int temp = 0;
			while(temp <= numberOfBombs - 1) {
				int x = rand.nextInt((Minesweeper.TileWidth - 1) + 1);
				int y = rand.nextInt((Minesweeper.TileHeight - 1) + 1);
				if(level[x][y].Bomb) {
					// its a bomb retry?
				} else {
					level[x][y].Bomb = true;
					temp++;
				}
			}
			TotalBombs+=numberOfBombs;
		}
	}
	
	// Mark Tile & Question Tile & Bomb Tile is toggable. Switches between marked and unmarked when called. --# Getters and Setters #--
	// Sets the "ID" of a Tile, 0 = Blank Tile
	public void setTileNumber(int Number, int x, int y) {
		level[x][y].Number = Number;
	}
	
	public void markTile(int x, int y) {
		if(level[x][y].Number == -1 || level[x][y].Number == 97) {
			if(level[x][y].Marked) {
				level[x][y].Number = -1;
				level[x][y].Marked = false;
			} else {
				level[x][y].Number = 97;
				level[x][y].Marked = true;
			}
		}
	}
	
	public void questionTile(int x, int y) {
		if(level[x][y].Question) {
			level[x][y].Question = false;
		} else {
			level[x][y].Question = true;
		}
	}
	
	public void bombTile(int x, int y) {
		if(level[x][y].Bomb) {
			level[x][y].Bomb = false;
		} else {
			level[x][y].Bomb = true;
		}
	}
}
