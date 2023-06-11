import java.awt.Color;

import javax.swing.JButton;

public class ColorThread extends Thread {

	private int[][] tileVals;
	private JButton[][] tiles;
	private Color lightFront;
	private Color darkFront;
	private boolean keepGoing = true;
	
	public ColorThread(int[][] tempTiles, JButton[][] buttonArr, Color lightF, Color darkF) {
		tileVals = tempTiles;
		tiles = buttonArr;
		lightFront = lightF;
		darkFront = darkF;
	}
	
	public void halt() {
		keepGoing = false;
	}
	
	@Override
	public void run() {
		while(keepGoing) {
			//System.out.println("Color Thread is Running");
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(tileVals[i][j] < 0) {
						tiles[i][j].setForeground(darkFront);
					}
					else {
						tiles[i][j].setForeground(lightFront);
					}
				}
			}
			try {
				Thread.sleep(10);	
			} catch (InterruptedException e) {
			}
		
	}
	}
	
	//public JButton[][] getValue(){
	//	return tiles;
	//}
	
	
}
