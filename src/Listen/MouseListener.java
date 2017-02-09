package Listen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Core.Minesweeper;

public class MouseListener {
	
	public static int MouseX,MouseY;
	public static boolean leftButtonDown;
	public static boolean rightButtonDown;
	public static boolean clicked = false;
	public static boolean rightClicked;
	
	public static void tick() {
		MouseX = Mouse.getX() - Minesweeper.BoardOffsetX;
		MouseY = Math.abs(Mouse.getY() - Display.getHeight()) - Minesweeper.BoardOffsetY;
		while(Mouse.next()) {
			if(Mouse.getEventButton() == 0) {
				if(!Mouse.getEventButtonState()) {
					if(clicked = true) {
						
					} else {
						clicked = true;
					}
				}
			}
			if(Mouse.getEventButton() == 1) {
				if(!Mouse.getEventButtonState()) {
					if(rightClicked = true) {
						
					} else {
						rightClicked = true;
					}
				}
			}
		}
	}
}
