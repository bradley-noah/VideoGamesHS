import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class GamePanel extends JPanel{
	
	private JFrame game;

	private ArrayList<JButton> buttonList = new ArrayList<JButton>();
 	ImageIcon tempSnakeIcon = new ImageIcon(this.getClass().getResource("Snake.png"));
	Image snkImg = tempSnakeIcon.getImage();
	Image newSnk = snkImg.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon snakeIcon = new ImageIcon(newSnk);
	
	ImageIcon tempChessIcon = new ImageIcon(this.getClass().getResource("Chess.png"));
	Image chsImg = tempChessIcon.getImage();
	Image newChs = chsImg.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chessIcon = new ImageIcon(newChs);
	
	ImageIcon tempPongIcon = new ImageIcon(this.getClass().getResource("Pong.png"));
	Image pngImg = tempPongIcon.getImage();
	Image newPng = pngImg.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon pongIcon = new ImageIcon(newPng);
	
	ImageIcon tempMarioIcon = new ImageIcon(this.getClass().getResource("Mario.jpg"));
	Image marioImg = tempMarioIcon.getImage();
	Image newMario = marioImg.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon marioIcon = new ImageIcon(newMario);
	
	private JButton snakeButton = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			new SnakeFrame();
			game.setState(JFrame.ICONIFIED);
		}
	});
	private JButton chessButton = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			ChessThread chess = new ChessThread();
			chess.start();
			game.setState(JFrame.ICONIFIED);
		}
	});
	private JButton pongButton = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			new PongFrame();
			game.setState(JFrame.ICONIFIED);
		}
	});
	private JButton marioButton = new JButton(new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			MarioThread mario = new MarioThread();
			mario.start();
			game.setState(JFrame.ICONIFIED);
		}
	});
	
	public Icon getIcon(int key) {
		if(key == 0) {
			return snakeIcon;
		}
		else if(key == 1) {
			return chessIcon;
		}
		else if(key == 2) {
			return pongIcon;
		}
		else if(key == 3) {
			return marioIcon;
		}
		else return null;
	}
	
	public GamePanel(GameFrame thisFrame) {
		
		game = thisFrame;
		
		buttonList.add(snakeButton);
		buttonList.add(chessButton);
		buttonList.add(pongButton);
		buttonList.add(marioButton);
		
		for(int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setIcon(getIcon(i));
		}
		//snakeButton.setIcon(snakeIcon);
		//chessButton.setIcon(chessIcon);
		//chessButton.setHorizontalAlignment(SwingConstants.CENTER);
		//System.out.println("here");
		this.setPreferredSize(new Dimension (600, 400));
		
		this.setLayout(new GridLayout(2, 2, 0, 0));
		this.setBackground(Color.BLACK);
		this.add(snakeButton);
		this.add(chessButton);
		this.add(pongButton);
		this.add(marioButton);
		//validate();
		
	}
	
}
