package World;

import Core.Minesweeper;

public class Tile {

	public int Number = 0;
	public boolean Bomb, Marked, Question, Checked;
	
	public Tile(int Number, boolean Bomb, boolean Marked, boolean Question, boolean Checked) {
		this.Number = Number;
		this.Bomb = Bomb;
		this.Marked = Marked;
		this.Question = Question;
		this.Checked = Checked;
	}
	
	public void render(int x, int y) {
		// Color color = new Color(255,255,255);
		Minesweeper.tiles.tileSet.get(Number).draw(x * Minesweeper.TileSize + Minesweeper.BoardOffsetX, y * Minesweeper.TileSize + Minesweeper.BoardOffsetY, Minesweeper.TileSize, Minesweeper.TileSize);
	}
}
