/*
 * This is the class that configures the JFrame that holds the JPanel for 
 * the Mario-esque game. It sets the frame size and location on screen.
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MarioFrame extends JFrame{

	public MarioFrame() {
		
		try{
			   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 }catch(Exception e){
			  e.printStackTrace(); 
			 }
			
		MarioPanel p = new MarioPanel(this);
		//System.out.println("here");
		this.add(p);
		this.setTitle("Mario");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//validate();
	}
	
	public static void main (String[] args) {
		new MarioFrame();
	}
	
}
