import javax.swing.JFrame;

public class PromotionFrame extends JFrame {

	private PromotionPanel holderP;
	
	public PromotionPanel getPanel() {
		return holderP;
	}
	
	public PromotionFrame() {
		
		PromotionPanel p = new PromotionPanel();
		this.add(p);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		holderP = p;
		
	}
	
}
