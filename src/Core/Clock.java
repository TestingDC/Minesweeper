package Core;

public class Clock extends Thread {
	
	public boolean isRunning = false;
	public final short ticksPerSecond = 20;
	public long totalTicks = 0;
	
	public void run() {
		isRunning = true;
		System.out.println("Starting Game Clock #");
		
		while(isRunning) {
			try {
				Thread.sleep(1000/ticksPerSecond);
				if(Minesweeper.game.gameOver == false) {
					Minesweeper.game.update();
				}
			} catch (InterruptedException e) { 
				System.out.println("Thread was Interrupted Closing.. Thread Name: " + this.getName());
				isRunning = false;
			}
			totalTicks++;
		}
	}
}
