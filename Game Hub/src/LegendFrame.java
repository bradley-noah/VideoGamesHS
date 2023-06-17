/*
 * Class for holding the legend panel using/extending JFrame
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
