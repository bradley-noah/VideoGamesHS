import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class PromotionCheck extends Thread {

	private int[][] tileVals;
	private int val = 0;
	private int xCoord;
	private int yCoord;
	private ChessPanel board;
	private boolean foundBottom = false;
	private static volatile boolean needsPromotion = false;
	private static volatile int pieceNum;
	private boolean keepGoing = true;
	
	public PromotionCheck(int[][] tempTiles, ChessPanel tempBoard) {
		tileVals = tempTiles;
		board = tempBoard;
	}
	
	public int getVal() {
		return val;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public boolean getPromo() {
		return needsPromotion;
	}
	
	public void setPromo(boolean tempPromo) {
		needsPromotion = tempPromo;
	}
	
	public void halt() {
		keepGoing = false;
	}
	
	@Override
	public void run() {
		boolean promotionFound = false;
		while(keepGoing) {
		while(keepGoing && promotionFound == false) {
			//System.out.println("Promotion Check Running");
			for(int i = 0; i < 8; i++) {
				if(tileVals[i][0] == -1) {
					try {
						Thread.sleep(100);	
					} catch (InterruptedException e) {
					}
						if(tileVals[i][0] == -1) {
							xCoord = i;
							yCoord = 0;
							foundBottom = true;
							promotionFound = true;
						}
					//System.out.println("(" + i + ", " + 0 + ")");
				}
				else if(tileVals[i][7] == 1) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
						if(tileVals[i][7] == 1) {
							xCoord = i;
							yCoord = 7;
							foundBottom = false;
							promotionFound = true;
						}
					//System.out.println("(" + i + ", " + 7 + ")");
				}	
			}
		}
		if(promotionFound) {
			promotionFound = false;
			PromotionFrame p = new PromotionFrame();
			p.setLocation(board.getFrame().getX() + board.getFrame().getWidth(), board.getFrame().getY() + (board.getFrame().getHeight() / 2) - (p.getHeight() / 2) + 11);		
			pieceNum = p.getPanel().getPieceKey();
			while(pieceNum == 0) {
					pieceNum = p.getPanel().getPieceKey();
			}
			
				if(foundBottom) {
					val = -1 * p.getPanel().getPieceKey();
					tileVals[xCoord][yCoord] = -val;
					//System.out.println("val inside check: " + val);
				}
				else {
					val = p.getPanel().getPieceKey();
					tileVals[xCoord][yCoord] = val;
					//System.out.println("val inside check: " + val);
				}
				needsPromotion = true;
				p.dispose();
			
			
		}
		}
	}
	
}
