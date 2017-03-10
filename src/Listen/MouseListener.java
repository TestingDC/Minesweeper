package Listen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Core.Game;

public class MouseListener {
	
	public static int MouseX,MouseY;
	public static boolean leftButtonDown;
	public static boolean rightButtonDown;
	public static boolean clicked = false;
	public static boolean rightClicked;
	
	public static void tick() {
		MouseX = Mouse.getX() - Game.boardOffsetX;
		MouseY = Math.abs(Mouse.getY() - Display.getHeight()) - Game.boardOffsetY;
		
		
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
	
	public static void clear() {
		leftButtonDown = false;
		rightButtonDown = false;
		clicked = false;
		rightClicked = false;
		Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight());
	}
}
