import java.util.concurrent.TimeUnit;

public class KingCheck extends Thread{

	private int[][] tileVals;
	private int winnerNum;
	private boolean keepGoing = true;
	
	public KingCheck(int[][] tempTiles) {
		tileVals = tempTiles;
	}
	
	public int getWinner() {
		return winnerNum;
	}
	
	public void halt() {
		keepGoing = false;
	}
	
	@Override
	public void run() {
		
		/*
		System.out.println(tileVals[0][0]);
		System.out.println(tileVals[1][0]);
		System.out.println(tileVals[2][0]);
		System.out.println(tileVals[3][0]);
		System.out.println(tileVals[4][0]);
		System.out.println(tileVals[5][0]);
		System.out.println(tileVals[6][0]);
		System.out.println(tileVals[7][0]);
		*/
		//boolean kingDead = true;
		while(!Thread.currentThread().isInterrupted() && keepGoing) {
			//System.out.println("King Check is Running");
		boolean	kingDead = true;
		boolean evilKingDead = true;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//System.out.println(y.getTileVals(i, j));
				if(tileVals[i][j] == 6) {
					kingDead = false;
				}
				if(tileVals[i][j] == -6) {
					evilKingDead = false;
				}
			}
			//System.out.println(tileVals[i][j]);
		}
		//System.out.println("Just Checked all the tiles");
		if(kingDead) {
			System.out.println("The King is Dead");
			winnerNum = 2;
			throw new RuntimeException();
			//this.interrupt();
			//this.stop();
			//System.exit(1);
		}
		else if(evilKingDead) {
			System.out.println("The Evil King is Dead");
			winnerNum = 1;
			throw new RuntimeException();
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		}
	}

	
		




}
	
	
