/*
 * The purpose of this class is to have the GamePanel run the MarioGame
 * on a separate thread to eliminate issues with concurrent running of
 * the ChessPanel
 */
public class MarioThread extends Thread{

	public MarioThread() {
		
	}
	
	public void run() {
		MarioFrame m = new MarioFrame();
	}
	
}
