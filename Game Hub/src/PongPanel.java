import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class PongPanel extends JPanel implements ActionListener{

	private JFrame mainFrame;
	
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 400;
	private static final int PUCK_SIZE = 25;
	//static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	private static final int DELAY = 120;
	private final int MINIMUM_VELOCITY = 1;
	private int velX = MINIMUM_VELOCITY;
	private int velY = MINIMUM_VELOCITY;
	private final int MAX_SCORE = 8;
	Timer timer;
	volatile int leftPaddleY;
	volatile int rightPaddleY;
	private final int PADDLE_WIDTH = 16;
	private final int PADDLE_HEIGHT = 120;
	private final int LEFT_PADDLE_X = 5;
	private final int RIGHT_PADDLE_X = SCREEN_WIDTH - PADDLE_WIDTH - LEFT_PADDLE_X;
	private final int TERMINAL_VELOCITY = 8;
	private boolean rightUp = false;
	private boolean rightDown = false;
	private boolean leftUp = false;
	private boolean leftDown = false;
	private volatile boolean running = true;
	volatile int puckX = SCREEN_WIDTH/2 - (PUCK_SIZE/2);
	volatile int puckY = SCREEN_HEIGHT/2 - (PUCK_SIZE/2);
	private int leftScore = 0;
	private int rightScore = 0;
	
	
	

	
	
	public PongPanel(PongFrame thisFrame) {
		
		mainFrame = thisFrame;
		//System.out.println(9/2);
		//Random rand = new Random();
		PongThread pt = new PongThread();
		pt.start();
		//snakeButton.setIcon(snakeIcon);
		//chessButton.setIcon(chessIcon);
		//chessButton.setHorizontalAlignment(SwingConstants.CENTER);
		//System.out.println("here");
		this.setPreferredSize(new Dimension (SCREEN_WIDTH, SCREEN_HEIGHT));
		
		//this.setLayout(new GridLayout(1, buttonList.size(), 0, 0));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		//this.addKeyListener(pt. new MyKeyAdapter2());
		//this.addKeyListener(new MyKeyAdapter2());
		
		timer = new Timer(DELAY, this);
		timer.start();
		//move();
	}
	
	public void move() {
		
				
	}
	
	public void incrementY() {
		if(Math.abs(velY) < TERMINAL_VELOCITY - 4) {
			if(velY >= 0 ) {
				velY++;
			}
			else {
				velY--;
			}
		}
	}
	
	public void decrementY() {
		if(Math.abs(velY) > MINIMUM_VELOCITY) {
		if(velY >= 0) {
			velY--;
		}
		else velY++;
		}
	}
	
	public void incrementX() {
		
			if(velX >= 0 ) {
				if(velX >= MINIMUM_VELOCITY + ((TERMINAL_VELOCITY - MINIMUM_VELOCITY) / 2)) {
					velX++;
				}
				velX++;
			}
			else {
				if(velX <= -5) {
					velX--;
				}
				velX--;
			}
			if(Math.abs(velX) > TERMINAL_VELOCITY) {
				velX = (int) (Math.signum(velX) * TERMINAL_VELOCITY);
			}
	}
	
	
	public void decrementX() {
		if(Math.abs(velX) > MINIMUM_VELOCITY) {
		if(velX >= 0) {
			velX--;
		}
		else velX++;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if(running) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ink Free", Font.BOLD, 25));
			FontMetrics metrics1 = getFontMetrics(g.getFont());
			g.drawString(leftScore + "         " + rightScore, 
					(SCREEN_WIDTH - metrics1.stringWidth(leftScore + "         " + rightScore))/2, 90);
		
			g.setColor(Color.RED);
			if(Math.abs(velX) == TERMINAL_VELOCITY) {
				g.setColor(Color.WHITE);
				g.fillOval(puckX - (2*velX), puckY - (2*velY), PUCK_SIZE, PUCK_SIZE);
				g.setColor(Color.ORANGE);
				g.fillOval(puckX - (velX), puckY - (velY), PUCK_SIZE, PUCK_SIZE);
				g.setColor(Color.RED);
			}
			else if(Math.abs(velY) == TERMINAL_VELOCITY - 4) {
				g.setColor(Color.CYAN);
			}
			if(Math.abs(velX) == TERMINAL_VELOCITY && Math.abs(velY) == TERMINAL_VELOCITY - 4) {
				g.setColor(Color.MAGENTA);
			}
			g.fillOval(puckX, puckY, PUCK_SIZE, PUCK_SIZE);
		
			g.setColor(Color.WHITE);
			g.fillRect(LEFT_PADDLE_X, leftPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		
			g.setColor(Color.WHITE);
			g.fillRect(RIGHT_PADDLE_X, rightPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		}
		else gameOver(g);
	}

	public void gameOver(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", 
				(SCREEN_WIDTH - metrics1.stringWidth("GAME OVER"))/2, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if(running) {
			repaint();
		//}
			
		
		
		System.out.println("In Loop");
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {	
			switch(e.getKeyCode()) {
			//case KeyEvent.VK_LEFT:
			//	if(direction != 'R') {
			//		direction = 'L';
			//	}
			//	break;
			//case KeyEvent.VK_RIGHT:
			//	if(direction != 'L') {
			//		direction = 'R';
			//	}
			//	break;	
			/*
			case KeyEvent.VK_UP:
				rightPaddleY-=10;
				break;
			case KeyEvent.VK_DOWN:
				rightPaddleY+=10;
				break;
				*/
			case KeyEvent.VK_W:
				leftUp = true;
				break;
			case KeyEvent.VK_S:
				leftDown = true;
				break;
			case KeyEvent.VK_UP:
				rightUp = true;
				break;
			case KeyEvent.VK_DOWN:
				rightDown = true;
				break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				leftUp = false;
				//System.out.println("W Key Released");
				break;
			case KeyEvent.VK_S:
				leftDown = false;
				//System.out.println("S Key Released");
				break;
			case KeyEvent.VK_UP:
				rightUp = false;
				//System.out.println("UP Key Released");
				break;
			case KeyEvent.VK_DOWN:
				rightDown = false;
				//System.out.println("DOWN Key Released");
				break;
			}
		}
		
	}
	public class PongThread extends Thread{

		private boolean keepGoing = true;
		
		PongThread() {	//boolean tempLeftUp, boolean tempLeftDown, boolean tempRightUp, boolean tempRightDown){
			
			
			
		}
		
		public void halt() {
			keepGoing = false;
		}
		
		public void run(){
			while(keepGoing) {
				if(puckY > 0 && puckY < SCREEN_HEIGHT - PUCK_SIZE) {
					puckY+=velY;
				}
				//collision with top or bottom
				else {
					velY*=-1;
					puckY+=velY;
				}
				if(puckX > 0 && puckX < SCREEN_WIDTH - PUCK_SIZE) {
					//collision with left paddle
					if(puckX <= LEFT_PADDLE_X + PADDLE_WIDTH && puckY >= leftPaddleY && puckY <= leftPaddleY + PADDLE_HEIGHT) {
						//hits top or bottom quarter of paddle
						if(puckY < leftPaddleY + PADDLE_HEIGHT/6 || puckY > leftPaddleY + (5*PADDLE_HEIGHT/6)) {
							incrementY();
							decrementX();
						}
						//hits paddle in the middle
						else {
							decrementY();
							incrementX();
						}
						velX*=-1;
						puckX+=velX;
					}
					//collision with right paddle
					else if(puckX + PUCK_SIZE >= RIGHT_PADDLE_X && puckY >= rightPaddleY && puckY <= rightPaddleY + PADDLE_HEIGHT) {
						//hits top or bottom quarter of paddle
						if(puckY < rightPaddleY + PADDLE_HEIGHT/6 || puckY > rightPaddleY + (5*PADDLE_HEIGHT/6)) {
							incrementY();
							decrementX();
						}
						//hits paddle in the middle
						else {
							decrementY();
							incrementX();
						}
						velX*=-1;
						puckX+=velX;
					}
					else
					puckX+=velX;
				}
				//puck reaches the left or right goal
				else {		
					if(puckX < SCREEN_WIDTH/2) {	//left goal
						rightScore++;
						velX = -2;
					}
					else {							//right goal
						leftScore++;
						velX = 2;
					}
					puckX = SCREEN_WIDTH/2 - (PUCK_SIZE/2);
					puckY = SCREEN_HEIGHT/2 - (PUCK_SIZE/2);
					velY = 2;
					velX*=-1;
					//System.out.println("Left Score: " + leftScore + "   Right Score: " + rightScore);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				if(rightUp && rightPaddleY > 0) {
					rightPaddleY-=4;
				}
				else if(rightDown && rightPaddleY + PADDLE_HEIGHT < SCREEN_HEIGHT) {
					rightPaddleY+=4;
				}
				if(leftUp && leftPaddleY > 0) {
					leftPaddleY-=4;
				}
				else if(leftDown && leftPaddleY + PADDLE_HEIGHT < SCREEN_HEIGHT) {
					leftPaddleY+=4;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("Left Score: " + leftScore + "   Right Score: " + rightScore);
			if(leftScore == MAX_SCORE || rightScore == MAX_SCORE) {
				keepGoing = false;
				running = false;
				
			}
			//if(!mainFrame.isVisible()) {
			//	halt();
			//}
			System.out.println("In Thread Loop");
			}
			
			
		}
		

		
	}
	
/*	
public class MyKeyAdapter2 extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			//case KeyEvent.VK_LEFT:
			//	if(direction != 'R') {
			//		direction = 'L';
			//	}
			//	break;
			//case KeyEvent.VK_RIGHT:
			//	if(direction != 'L') {
			//		direction = 'R';
			//	}
			//	break;	
			case KeyEvent.VK_UP:
				rightUp = true;
				break;
			case KeyEvent.VK_DOWN:
				rightDown = true;
				break;
				
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				rightUp = false;
				System.out.println("UP Key Released");
				break;
			case KeyEvent.VK_DOWN:
				rightDown = false;
				System.out.println("DOWN Key Released");
				break;
			}
		}
		
	}
	*/
}
