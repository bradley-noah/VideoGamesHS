/*
 * Class that holds a JPanel that displays the text for the end of the chess game
 * Displays "GAME OVER" as well as the winner of the match
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LegendPanel extends JPanel{

	private int width = 250;
	private int height = 400;
	private int winnerNum;
	
	public LegendPanel(int winner) {
		
		this.setPreferredSize(new Dimension (width, height));
		this.setBackground(Color.BLACK);
		winnerNum = winner;
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.setFont(new Font("Courier", Font.BOLD, 20));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (width - metrics.stringWidth("GAME OVER"))/2, height/3);
		g.drawString("PLAYER " + winnerNum + " WON", (width - metrics.stringWidth("PLAYER " + winnerNum + " WON"))/2, 2 * height/3);
	}
	
}
