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
