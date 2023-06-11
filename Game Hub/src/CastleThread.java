
public class CastleThread extends Thread {

	private int[][] tileVals;
	private boolean castleRightReady = true;
	private boolean castleLeftReady = true;
	private boolean evilCastleRightReady = true;
	private boolean evilCastleLeftReady = true;
	private boolean keepGoing = true;
	
	public boolean okayToCastleR() {
		return castleRightReady;
	}
	
	public boolean okayToCastleL() {
		return castleLeftReady;
	}
	
	public boolean okayToCastleER() {
		return evilCastleRightReady;
	}
	
	public boolean okayToCastleEL() {
		return evilCastleLeftReady;
	}
	
	
	public CastleThread(int[][] tempTiles) {
		tileVals = tempTiles;
	}
	
	public void halt() {
		keepGoing = false;
	}
	
	public void run() {
		while(keepGoing) {
			//System.out.println("Castle Thread is Running");
			if(tileVals[0][0] != 4) {
				castleLeftReady = false;
				//System.out.println(tileVals[0][0]);
			}
			if(tileVals[7][0] != 4) {
				castleRightReady = false;
			}
			if(tileVals[3][0] != 6) {
				castleRightReady = false;
				castleLeftReady = false;
				//System.out.println(tileVals[3][0]);
			}	
			if(tileVals[0][7] != -4) {
				evilCastleLeftReady = false;
				//System.out.println(tileVals[0][0]);
			}
			if(tileVals[7][7] != -4) {
				evilCastleRightReady = false;
			}
			if(tileVals[3][7] != -6) {
				evilCastleRightReady = false;
				evilCastleLeftReady = false;
				//System.out.println(tileVals[3][0]);
			}
			//System.out.println("ER: " + evilCastleRightReady);
			//System.out.println("El: " + evilCastleLeftReady);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		
	}

	}
	
	
	
}
