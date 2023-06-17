/*
 * This is the class that configures the JFrame that holds the JPanel for 
 * the Legend, which displays the winner. 
 * It sets the frame size and location on screen.
 */
import javax.swing.JFrame;

public class LegendFrame extends JFrame{
	
	public LegendFrame(int num) {
		
		LegendPanel l = new LegendPanel(num);
		this.add(l);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		//this.setLocationRelativeTo(null);
		
		
	}
	
}
