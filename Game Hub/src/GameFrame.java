import javax.swing.JFrame;
import javax.swing.UIManager;

public class GameFrame extends JFrame{

	public GameFrame() {
		
		try{
			   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 }catch(Exception e){
			  e.printStackTrace(); 
			 }
			
		GamePanel p = new GamePanel(this);
		//System.out.println("here");
		this.add(p);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//validate();
	}
	
	public static void main (String[] args) {
		new GameFrame();
	}
}
