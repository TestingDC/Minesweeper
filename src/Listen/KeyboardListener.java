package Listen;

import org.lwjgl.input.Keyboard;

import Tools.DebugMenu;


public class KeyboardListener {
	
	public static boolean cancelF3;
	
	public static void tick() {
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) { // Press
				if ((Keyboard.isKeyDown(Keyboard.KEY_F3)) && (Keyboard.getEventKey() == Keyboard.KEY_G)) {
					cancelF3 = true;
					if(DebugMenu.toggleXray) { DebugMenu.toggleXray = false; } else { DebugMenu.toggleXray = true; }
				}
			} else { // Release
					
				if (Keyboard.getEventKey() == Keyboard.KEY_F3) {
					if(!cancelF3) {
						System.out.println("i");
						if(DebugMenu.debug) { DebugMenu.debug = false; } else { DebugMenu.debug = true; }
					}
					cancelF3 = false;
				}
			}
		}
	}
}