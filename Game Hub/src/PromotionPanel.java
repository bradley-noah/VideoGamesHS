import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PromotionPanel extends JPanel {

	private int width = 100;
	private int height = 390;
	private int pieceKey = 0;
	
	public int getPieceKey() {
		return pieceKey;
	}
	
	private JButton queenButton = new JButton(new AbstractAction("♛") {
		@Override
		public void actionPerformed(ActionEvent e) {
			pieceKey = 5;
			System.out.println(pieceKey);
		}
	});
	private JButton rookButton = new JButton(new AbstractAction("♖") {
		@Override
		public void actionPerformed(ActionEvent e) {
			pieceKey = 4;
		}
	});
	private JButton bishopButton = new JButton(new AbstractAction("♗") {
		@Override
		public void actionPerformed(ActionEvent e) {
			pieceKey = 3;
		}
	});
	private JButton knightButton = new JButton(new AbstractAction("♞") {
		@Override
		public void actionPerformed(ActionEvent e) {
			pieceKey = 2;
		}
	});
	
	public PromotionPanel() {
		
		this.setPreferredSize(new Dimension (width, height));
		this.setLayout(new GridLayout(4, 1, 0, 0));
		this.setBackground(Color.GRAY);
		
		Font font = new Font("Courier", Font.BOLD, 70);
		Color purp1 = new Color(172, 153, 193);
		Color purp2 = new Color(116, 89, 126).darker();
		Color back1 = new Color(116, 89, 126);
		Color back2 = new Color(172, 153, 193).brighter();
		
		queenButton.setFont(font);
		queenButton.setForeground(purp1);
		queenButton.setBackground(back1);
		queenButton.setOpaque(true);
		rookButton.setFont(font);
		rookButton.setForeground(purp2);
		rookButton.setBackground(back2);
		rookButton.setOpaque(true);
		bishopButton.setFont(font);
		bishopButton.setForeground(purp1);
		bishopButton.setBackground(back1);
		bishopButton.setOpaque(true);
		knightButton.setFont(font);
		knightButton.setForeground(purp2);
		knightButton.setBackground(back2);
		knightButton.setOpaque(true);
		
		
		this.setBackground(Color.BLACK);
		this.add(queenButton);
		this.add(rookButton);
		this.add(bishopButton);
		this.add(knightButton);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.setFont(new Font("Courier", Font.BOLD, 20));
		FontMetrics metrics = getFontMetrics(g.getFont());
		//g.drawString("GAME OVER", (width - metrics.stringWidth("GAME OVER"))/2, height/3);
		//g.drawString("PLAYER " + winnerNum + " WON", (width - metrics.stringWidth("PLAYER " + winnerNum + " WON"))/2, 2 * height/3);
	}
	
}
