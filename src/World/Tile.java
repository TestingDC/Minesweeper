package World;

import Core.Game;

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
		TileImport.tileSet.get(Number).draw(x * Game.TileSize + Game.boardOffsetX, y * Game.TileSize + Game.boardOffsetY, Game.TileSize, Game.TileSize);
	}
}
