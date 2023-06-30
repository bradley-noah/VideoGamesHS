/*
 * This is the class that configures the JFrame that holds the JPanel for 
 * the snake game. It sets the frame size and location on screen.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeFrame extends JFrame{

	JButton[] buttons = new JButton[5];
	JPanel button_panel = new JPanel();
	JPanel area_panel = new JPanel();
	
public SnakeFrame() {
	SnakePanel panel = new SnakePanel();
	this.add(panel);
	this.setTitle("Snake");
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setResizable(false);
	this.pack();
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	
	
	

}
	
}
