import javax.swing.JFrame;
import javax.swing.UIManager;

public class PongFrame extends JFrame{

	public PongFrame() {
		
		try{
			   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 }catch(Exception e){
			  e.printStackTrace(); 
			 }
			
		PongPanel p = new PongPanel(this);
		//System.out.println("here");
		this.add(p);
		this.setTitle("Pong");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		//this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		//validate();
	}
	
	public static void main (String[] args) {
		new PongFrame();
	}
	
}
