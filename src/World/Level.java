package World;

import java.util.Random;

import org.lwjgl.opengl.Display;

import Core.Minesweeper;
import Core.Game;
import Core.Game.GameMode;

public class Level {

	public Tile[][] level;
	public int TotalBombs = 0;
	private static boolean done = false;
	private static GameMode mode;
	
	public Level(int width, int height) {
		setGameMode(GameMode.NORMAL);
		level = new Tile[width][height];
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
	
	public boolean checkWin() {
		if(getUnmarkedTotalBombs() == 0 && getTotalMarks() == TotalBombs) { // Take this out of cursor update and put into main tick.
			for(int x = 0; x < level.length; x++) {
				for(int y = 0; y < level[0].length; y++) {
					if(level[x][y].Number == -1) {
						level[x][y].Number = getNearbyBombs(x, y);
						level[x][y].Checked = true;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean checkLose() {
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				if(level[x][y].Number == 99) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void clickBoard(int cx, int cy) {
		if(cx >= level.length || cx < 0 || cy >= level[0].length || cy < 0) {
			return;
		}
		
		updateBoard();
		
		// Click Logic // You lose.
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
			done = true;
			return;
		}
		// Clicked Powerup
		if(level[cx][cy].Question == true) {
			clickedMystery(cx, cy);
		}
		recursiveCheck(cx, cy);
	}
	
	public void recursiveCheck(int x, int y) {
		if(x > level.length - 1 || x < 0 || y > level[0].length - 1 || y < 0) {
			return;
		}
		if((level[x][y].Number == -1) && (level[x][y].Checked == false)) {
			if(getNearbyBombs(x, y) == 0) {
				level[x][y].Number = 0;
				level[x][y].Checked = true;
				recursiveCheck(x - 1, y - 1);
				recursiveCheck(x, y - 1);
				recursiveCheck(x + 1, y - 1);
				recursiveCheck(x - 1, y);
				recursiveCheck(x, y);
				recursiveCheck(x + 1, y);
				recursiveCheck(x - 1, y + 1);
				recursiveCheck(x, y + 1);
				recursiveCheck(x + 1, y + 1);
			} else {
				level[x][y].Number = getNearbyBombs(x, y);
				level[x][y].Checked = true;
				if(mode == GameMode.ARCADE) {
					randomQuestion(x, y);
				}
			}
		}
	}
	
	public void updateBoard() {
		// 94 93 92 91 90
		// Re-pop tiles that are unchecked.
		int[] power = { 94, 93, 92, 91, 90};
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[0].length; y++) {
				if(level[x][y].Checked == false && level[x][y].Marked == false && level[x][y].Question == false) {
					level[x][y].Number = -1;
				}
				for(int p : power) {
					if(level[x][y].Number == p) {
						level[x][y].Number = getNearbyBombs(x, y);
					}
				}
			}
		}
	}
	
	public void randomQuestion(int x, int y) {
		double rnInt = Math.random();
		if(rnInt < 0.05) { // 1%
			level[x][y].Number = 96;
			level[x][y].Question = true;
			level[x][y].Checked = false;
		} else { // 99%
			
		}
	}
	
	public void clickedMystery(int x, int y) {
		level[x][y].Question = false;
		level[x][y].Checked = true;
		Random rand = new Random();
		int power = rand.nextInt((5 - 1) + 1);
		
		switch(power) {
		case 0:
			level[x][y].Number = 94; // Bullseye // reveal 8 random tiles across the board.			
			for(int i = 0; i < 8; i++) {
				int xr = rand.nextInt((level.length - 1) + 1);
				int yr = rand.nextInt((level[0].length - 1) + 1);
				try {
					Minesweeper.renderUpdate();
					TileImport.tileSet.get(94).draw(xr * Game.TileSize + Game.boardOffsetX, yr * Game.TileSize + Game.boardOffsetY);
					Display.update();
					Thread.sleep(75);
				} catch (Exception e) { }
				
				if(level[xr][yr].Bomb) {
					if(level[xr][yr].Marked == false) {
						markTile(xr, yr);
					}
					level[xr][yr].Checked = true;
				} else {
					recursiveCheck(xr, yr);
				}
			}
			break;
		case 1:
			level[x][y].Number = 93; // Sonar // Flickers Bombs for 2 seconds
			for(int i = 0; i < 10; i++) {
				for(int xa = x - 3; xa < x + 3; xa++) {
					for(int ya = y - 3; ya < y + 3; ya++) {
						if(xa < 0 || xa > level.length - 1 || ya < 0 || ya > level[0].length - 1) {
							// OutofBounds.
						} else {
							if(level[xa][ya].Bomb) {
								Minesweeper.renderUpdate();
								TileImport.tileSet.get(99).draw(xa * Game.TileSize + Game.boardOffsetX, ya * Game.TileSize + Game.boardOffsetY);
								Display.update();
								try {
									Thread.sleep(50);
								} catch(Exception e) { }
							}
						}
					}
				}
			}
			break;
		case 2:
			level[x][y].Number = 92; // Roulette // ---
			for(int i = 0; i < 2; i++) {
				int xi = x;
				int yi = y;
				int dx = rand.nextInt((1 - (-1)));
				int dy = rand.nextInt((1 - (-1))); 
				for(int g = 0; g < 10; g++) {
					if(xi < 2 || xi > level.length - 2) {
						dx = -dx;
					}
					if(yi < 2 || yi > level[0].length - 2) {
						dy = -dy;
					}
					xi+=dx;
					yi+=dy;
					if(level[xi][yi].Bomb) {
						if(level[xi][yi].Marked == false) {
							markTile(xi, yi);
						}
						level[xi][yi].Checked = true;
					} else {
						recursiveCheck(xi, yi);
					}
					Minesweeper.renderUpdate();
					TileImport.tileSet.get(92).draw(xi * Game.TileSize + Game.boardOffsetX, yi * Game.TileSize + Game.boardOffsetY);
					Display.update();
					try {
						Thread.sleep(50);
					} catch(Exception e) { }
				}
			}
			break;
		case 3:
			level[x][y].Number = 91; // BombSpread // Spreads 3 Bombs nearby radius.
			break;
		case 4:
			level[x][y].Number = 90; // Amnesia //
			for(int i = 0; i < 5; i++) {
				int curX = x;
				int curY = y;
				for(int s = 0; s < 30; s++) {
					try {
						Thread.sleep(20);
						Minesweeper.renderUpdate();
						Display.update();
					} catch (Exception e) {
						
					}
					if(Math.random() < 0.5) {
						curX += (-1) + rand.nextInt((1 - (-1)) + 1);
					} else {
						curY += (-1) + rand.nextInt((1 - (-1)) + 1);
					}
					if(curX < 0) {
						curX = 0;
					}
					if(curY < 0) {
						curY = 0;
					}
					if(curX > level.length - 1) {
						curX = level.length - 1;
					}
					if(curY > level[0].length - 1) {
						curY = level[0].length - 1;
					}
					level[curX][curY].Checked = false;
					level[curX][curY].Marked = false;
					updateBoard();
					level[x][y].Number = 90;
					level[x][y].Checked = true;
				}
			}
			break;
		}
	}
	
	public int getNearbyBombs(int x, int y) {
		int BombAmount = 0;
		for(int xi = (x - 1); xi < (x + 2); xi++) {
			for(int yi = (y - 1); yi < (y + 2); yi++) {
				if(xi < 0 || xi >= level.length || yi < 0 || yi >= level[0].length) {
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
		if(numberOfBombs >= (level.length * level[0].length)) {
			System.out.println("Error: Number of Bombs must be less than the total tiles.");
			return;
		} else {
			Random rand = new Random();
			int temp = 0;
			while(temp <= numberOfBombs - 1) {
				int x = rand.nextInt((level.length - 1) + 1);
				int y = rand.nextInt((level[0].length - 1) + 1);
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
	
	public void generateGame(int numberOfBombs, GameMode mode) {
		switch(mode) {
		case NORMAL:
			generateBombs(numberOfBombs);
			setGameMode(GameMode.NORMAL);
			break;
		case ARCADE:
			generateBombs(numberOfBombs);
			setGameMode(GameMode.ARCADE);
			break;
		case TIMEATTACK:
			generateBombs(numberOfBombs);
			setGameMode(GameMode.TIMEATTACK);
			break;
		default:
			break;
		}
	}
	
	// Mark Tile & Question Tile & Bomb Tile is toggable. Switches between marked and unmarked when called. --# Getters and Setters #--
	// Sets the "ID" of a Tile, 0 = Blank Tile
	public boolean getGameOver() {
		return done;
	}
	
	public static GameMode getGameMode() {
		return mode;
	}

	public static void setGameMode(GameMode mode) {
		Level.mode = mode;
	}

	public void setTileNumber(int Number, int x, int y) {
		level[x][y].Number = Number;
	}
	
	public void markTile(int x, int y) {
		if(x > level.length - 1 || x < 0 || y > level[0].length - 1 || y < 0) {
			return;
		}
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
