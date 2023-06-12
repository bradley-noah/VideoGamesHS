/*
 * Thread functions as a pseudo chess frame, it holds the chess panel
 * The distinction is that it allows the chess game to run multiple threads 
 * concurrently without being an obstacle for other functions
 */
import javax.swing.UIManager;

public class ChessThread extends Thread{

	public ChessThread() {
			
	}
	
	public void run() {
		try{
			UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
			String lookAndFeelClassName = looks[2].getClassName();
			UIManager.setLookAndFeel(lookAndFeelClassName);	//UIManager.getCrossPlatformLookAndFeelClassName());
			}catch(Exception e){
			  e.printStackTrace(); 
			 }
		
		ChessPanel p = new ChessPanel();
	}
	
}
