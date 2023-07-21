/*
 * The purpose of this class is to create a JPanel, to add to a JFrame
 * It contains the underlying logic behind the entire game
 * the snake, the apples, the game rules, and ending cases 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener{

	
	static final int SCREEN_WIDTH = 720;
	static final int SCREEN_HEIGHT = 720;
	static final int UNIT_SIZE = 30;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 120;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 4;
	int applesEaten;
	int appleX;
	int appleY;
	int gappleX;
	int gappleY;
	int gapplesEaten = 0;
	int orangeX;
	int orangeY;
	int greenX;
	int greenY;
	int purpleX;
	int purpleY;
	int blueX;
	int blueY;
	int grayX;
	int grayY;
	int color = 0;
	char direction = 'R';
	boolean running = false;
	boolean terminated = true;
	Timer timer;
	Random random;
	JButton[] buttons = new JButton[5];
	JPanel button_panel = new JPanel();
	JPanel area_panel = new JPanel();
	
	public SnakePanel() {


		
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
		//button_panel.setLayout(new GridLayout(1,5));
		//button_panel.setBackground(new Color(150, 150, 150));
		//frame.add(button_panel);
	}
	public void startGame() {
		newApple();
		newColorBlock();
		newGapple();
		//JButton snakeColor = new JButton();
		//snakeColor.setBackground(Color.gray);
		//snakeColor.setText("Snake Color");
		//panel.add(snakeColor);
		running = true;
		terminated = false;
		/*
		button_panel.setLayout(new GridLayout(1,5));
		button_panel.setBackground(new Color(150, 15, 150));
		area_panel.setLayout(new BorderLayout());
		area_panel.setBackground(Color.red);
		//area_panel.setBounds(000, 000, 10000, 10000);
		
		for(int i = 0; i < 5; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		area_panel.add(button_panel);
		//this.add(area_panel);
		this.add(area_panel, BorderLayout.SOUTH);
		*/
		timer = new Timer(DELAY, this);
		timer.start();
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if(running){
			g.setColor(Color.gray);
			// for drawing grid lines
			/*for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}*/	
			//drawing apple
			g.setColor(Color.red);
			g.fillOval(appleX,appleY, UNIT_SIZE, UNIT_SIZE);
			
			//drawing Gapple
			if(((applesEaten)%10) == 0 && applesEaten != 0) {
				g.setColor(new Color(255, 215, 1));
				g.fillOval(gappleX,gappleY, UNIT_SIZE, UNIT_SIZE);
			}
			
			//drawing orange block
			g.setColor(new Color(255, 120, 1));
			g.fillOval(orangeX,orangeY, UNIT_SIZE, UNIT_SIZE);
			//drawing green block
			g.setColor(Color.green);
			g.fillOval(greenX,greenY, UNIT_SIZE, UNIT_SIZE);
			//drawing purple block
			g.setColor(new Color(120, 10, 200));
			g.fillOval(purpleX,purpleY, UNIT_SIZE, UNIT_SIZE);
			//drawing blue block
			g.setColor(Color.blue);
			g.fillOval(blueX,blueY, UNIT_SIZE, UNIT_SIZE);
			//drawing gray block
			g.setColor(Color.gray);
			g.fillOval(grayX,grayY, UNIT_SIZE, UNIT_SIZE);
			
			
			//white snake
			if(color == 0) {
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(new Color(255, 255, 255));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(random.nextInt(50) + 200, random.nextInt(50) + 200, random.nextInt(50) + 200));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			}
			//orange snake
			else if(color == 1) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(255, 100, 1));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						g.setColor(new Color(random.nextInt(25) + 200, random.nextInt(50) + 75, random.nextInt(10)));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			//green snake
			else if(color == 2) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(10, 200, 40));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						g.setColor(new Color(1, random.nextInt(100) + 100, random.nextInt(75)));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			//purple snake
			else if(color == 3) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(120, 0, 180));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						g.setColor(new Color(random.nextInt(50) + 80, random.nextInt(20), random.nextInt(40) + 100));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			//blue snake
			else if(color == 4) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(40, 0, 180));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						g.setColor(new Color(random.nextInt(30) + 10, random.nextInt(10), random.nextInt(60) + 140));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			//gray snake
			else if(color == 5) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(120, 120, 120));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						g.setColor(new Color(random.nextInt(20) + 125, random.nextInt(20) + 125, random.nextInt(20) + 125));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			//hard mode snake
			else if(color == 6) {
				for(int i = 0; i < bodyParts; i++) {
					if(i == 0) {
						g.setColor(new Color(20, 20, 20));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
					else {
						//int col1 = random.nextInt(250);
						//int col2 = random.nextInt(4) + 1;
						//int col3 = random.nextInt(2) + 1;
						//g.setColor(new Color(col1/col3 + col2, (255 - col1)/col2, 255 - (col1/col2)));
						//g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
						g.setColor(new Color((bodyParts * (random.nextInt(50))) % 255, random.nextInt(16), random.nextInt(16)));
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free", Font.BOLD, 25));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, SCREEN_HEIGHT/7);
		}
		else {
			gameOver(g);
		}
	}
	public void newColorBlock() {
		orangeX = (SCREEN_WIDTH/2)+UNIT_SIZE;
		orangeY = (SCREEN_HEIGHT/2);
		greenX = (SCREEN_WIDTH/2)+(2*UNIT_SIZE);
		greenY = (SCREEN_HEIGHT/2);
		purpleX = (SCREEN_WIDTH/2)-UNIT_SIZE;
		purpleY = (SCREEN_HEIGHT/2);
		blueX = (SCREEN_WIDTH/2)-(2*UNIT_SIZE);
		blueY = (SCREEN_HEIGHT/2);
		grayX = (SCREEN_WIDTH/2);
		grayY = (SCREEN_HEIGHT/2);
	}
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

	}
	public void newGapple() {
			gappleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
			gappleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
			/*if(gappleX == appleX || gappleY == appleY) {
				gappleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
				gappleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
			}*/
		
	}
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	public void checkColor() {
		if((x[0] == orangeX) && (y[0] == orangeY)) {
			color = 1;
			removeColorBlocks();
		}
		else if((x[0] == greenX) && (y[0] == greenY)){
			color = 2;
			removeColorBlocks();
		}
		else if((x[0] == purpleX) && (y[0] == purpleY)){
			color = 3;
			removeColorBlocks();
		}
		else if((x[0] == blueX) && (y[0] == blueY)){
			color = 4;
			removeColorBlocks();
		}
		else if((x[0] == grayX) && (y[0] == grayY)){
			color = 5;
			removeColorBlocks();
		}
	}
	public void removeColorBlocks() {
		orangeX = -100;
		orangeY = -100;
		greenX = -100;
		greenY = -100;
		purpleX = -100;
		purpleY = -100;
		blueX = -100;
		blueY = -100;
		grayX = -100;
		grayY = -100;
		
	
		
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts = bodyParts + 1;
			applesEaten++;
			newApple();
		}
	}
	public void checkGapple() {
		if((x[0] == gappleX) && (y[0] == gappleY)) {
			bodyParts = bodyParts + 5;
			applesEaten++;
			gapplesEaten++;
			newGapple();
			color = random.nextInt(7);
			//System.out.println(color);
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				bodyParts--;
				applesEaten--;
			}
		}
		//checks if head collides with left border
		if(x[0] < 0)
			running = false;
		//checks if head collides with right border
		if(x[0] > SCREEN_WIDTH)
			running = false;
		//checks if head collides with upper border
		if(y[0] < 0)
			running = false;
		//checks if head collides with lower border
		if(y[0] > SCREEN_HEIGHT)
			running = false;
		
		if(!running) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		//Score Text
		g.setColor(Color.orange);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, SCREEN_HEIGHT/7);
		//Game Over Text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkColor();
			if(((applesEaten)%10) == 0 && applesEaten != 0) {
				checkGapple();
			}
			checkCollisions();
		}
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;	
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
		
	}


}
