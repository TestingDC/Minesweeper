package Menu;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Button {

	private Image image = null;
	int x, y, width, height;
	
	Color clickedColor = new Color(255, 255, 255);
	Rectangle bounds = new Rectangle();
	
	public Button(int x, int y, int width, int height, Image image) {
		this.image = image;
		this.image.setFilter(Image.FILTER_NEAREST);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds.setBounds(this.x, this.y, this.width, this.height);
	}
	
	public boolean wasClicked() {
		int cx = Mouse.getX();
		int cy = Math.abs(Mouse.getY() - Display.getHeight());
		if(bounds.contains(cx, cy)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void render() {
		image.draw(this.x - (this.width/2), this.y - (this.height/2), this.width, this.height, clickedColor);
	}
}
