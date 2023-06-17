/*
 * The purpose of this class is to create a JPanel, to add to a JFrame
 * It contains a plethora of GameObjects and two PlayerObjects, 
 * and the implementation of these along with sound and layout forms
 * the structure of the Mario Game
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class MarioPanel extends JPanel implements ActionListener{

	private JFrame game;

	private static final int DOWN_SHIFT = 00;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 400 + DOWN_SHIFT;
	private static final int DELAY = 120;
	volatile int marioX = SCREEN_WIDTH/2;
	volatile int marioY = SCREEN_HEIGHT/3;
	private boolean right = false;
	private boolean left = false;
	Timer timer;
	private int velocity = 1;
	private final int TERMINAL_VELOCITY = 4;
	private final int JUMP_HEIGHT = 100;
	private int block1X = 200;
	private int block1Y = 200;
	private final int block1W = 50;
	private final int block1H = 20;
	private final int marioW = 30;
	private final int marioH = 30;
	//private int redVal = 120;
	private int grnVal = 60;
	private int grnChange = 3;
	private boolean doubleJump = false;
	private boolean gameOver = false;
	ArrayList<GameObject> blocks = new ArrayList<GameObject>();
	ArrayList<GameObject> coins = new ArrayList<GameObject>();
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<GameObject> lava = new ArrayList<GameObject>();
	GameObject block1 = new GameObject(block1X, block1Y + DOWN_SHIFT, block1W, block1H, TERMINAL_VELOCITY);
	GameObject block2 = new GameObject(300, 300 + DOWN_SHIFT, 200, 200, TERMINAL_VELOCITY);
	GameObject block3 = new GameObject(440, 190 + DOWN_SHIFT, 75, 20, TERMINAL_VELOCITY);
	GameObject block4 = new GameObject(140, 280 + DOWN_SHIFT, 125, 200, TERMINAL_VELOCITY);
	GameObject block5 = new GameObject(550, 340 + DOWN_SHIFT, 800, 200, TERMINAL_VELOCITY);
	GameObject block6 = new GameObject(1400, 300 + DOWN_SHIFT, 250, 200, TERMINAL_VELOCITY);
	GameObject block7 = new GameObject(1750, 250 + DOWN_SHIFT, 450, 200, TERMINAL_VELOCITY);
	GameObject block8 = new GameObject(1090, 210 + DOWN_SHIFT, 150, 20, TERMINAL_VELOCITY);
	GameObject block9 = new GameObject(1110, 130 + DOWN_SHIFT, 110, 20, TERMINAL_VELOCITY);
	GameObject block10 = new GameObject(1130, 50 + DOWN_SHIFT, 70, 20, TERMINAL_VELOCITY);
	GameObject block11 = new GameObject(1150, -230 + DOWN_SHIFT, 30, 220, TERMINAL_VELOCITY);
	GameObject block12 = new GameObject(1650, 450 + DOWN_SHIFT, 140, 20, TERMINAL_VELOCITY);
	GameObject block13 = new GameObject(-200, -180 + DOWN_SHIFT, 10, 1180, TERMINAL_VELOCITY);
	GameObject block14 = new GameObject(-80, 250 + DOWN_SHIFT, 80, 80, TERMINAL_VELOCITY);
	GameObject block15 = new GameObject(1350, 340 + DOWN_SHIFT, 4, 400, TERMINAL_VELOCITY);
	GameObject block16 = new GameObject(1399, 300 + DOWN_SHIFT, 4, 440, TERMINAL_VELOCITY);
	GameObject block17 = new GameObject(1150, 740 + DOWN_SHIFT, 204, 4, TERMINAL_VELOCITY);
	GameObject block18 = new GameObject(1399, 740 + DOWN_SHIFT, 204, 4, TERMINAL_VELOCITY);
	GameObject block19 = new GameObject(1150, 740 + DOWN_SHIFT, 4, 200, TERMINAL_VELOCITY);
	GameObject block20 = new GameObject(1603, 740 + DOWN_SHIFT, 4, 200, TERMINAL_VELOCITY);
	GameObject block21 = new GameObject(1150, 940 + DOWN_SHIFT, 457, 4, TERMINAL_VELOCITY);
	GameObject block22 = new GameObject(1330, 840 + DOWN_SHIFT, 40, 4, TERMINAL_VELOCITY);
	
	
	GameObject coin1 = new GameObject(570, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin2 = new GameObject(610, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin3 = new GameObject(650, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin4 = new GameObject(690, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin5 = new GameObject(730, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin6 = new GameObject(770, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin7 = new GameObject(810, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin8 = new GameObject(850, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin9 = new GameObject(890, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin10 = new GameObject(930, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin11 = new GameObject(970, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin12 = new GameObject(1010, 310 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	
	
	//1150 to 1607 lowest row
	GameObject coin13 = new GameObject(1180, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin14 = new GameObject(1240, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin15 = new GameObject(1300, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin16 = new GameObject(1360, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin17 = new GameObject(1420, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin18 = new GameObject(1480, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin19 = new GameObject(1540, 910 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	//second row
	GameObject coin20 = new GameObject(1210, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin21 = new GameObject(1270, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin22 = new GameObject(1330, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin23 = new GameObject(1390, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin24 = new GameObject(1450, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin25 = new GameObject(1510, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin26 = new GameObject(1570, 880 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	//third row
	GameObject coin27 = new GameObject(1180, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin28 = new GameObject(1240, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin29 = new GameObject(1300, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin30 = new GameObject(1360, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin31 = new GameObject(1420, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin32 = new GameObject(1480, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin33 = new GameObject(1540, 850 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	//fourth row
	GameObject coin34 = new GameObject(1210, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin35 = new GameObject(1270, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin36 = new GameObject(1330, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin37 = new GameObject(1390, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin38 = new GameObject(1450, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin39 = new GameObject(1510, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin40 = new GameObject(1570, 820 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	//fifth row
	GameObject coin41 = new GameObject(1180, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin42 = new GameObject(1240, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin43 = new GameObject(1300, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin44 = new GameObject(1360, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin45 = new GameObject(1420, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin46 = new GameObject(1480, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin47 = new GameObject(1540, 790 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	//sixth row
	GameObject coin48 = new GameObject(1210, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin49 = new GameObject(1270, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin50 = new GameObject(1330, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin51 = new GameObject(1390, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin52 = new GameObject(1450, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin53 = new GameObject(1510, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin54 = new GameObject(1570, 760 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	
	GameObject coin55 = new GameObject(-203, -205 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin56 = new GameObject(2000, 220 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	GameObject coin57 = new GameObject(block11.getX() + 7, block11.getY() - 180 + DOWN_SHIFT, 16, 20, TERMINAL_VELOCITY);
	
	GameObject sign = new GameObject(2080, 210 + DOWN_SHIFT, 120, 30, TERMINAL_VELOCITY);
	GameObject signPost = new GameObject(2136, 240 + DOWN_SHIFT, 8, 10, TERMINAL_VELOCITY);
	
	GameObject block23 = new GameObject(2300, 250 + DOWN_SHIFT, 50, 200, TERMINAL_VELOCITY);
	GameObject block24 = new GameObject(2400, 250 + DOWN_SHIFT, 50, 180, TERMINAL_VELOCITY);
	GameObject block25 = new GameObject(2500, 250 + DOWN_SHIFT, 50, 160, TERMINAL_VELOCITY);
	GameObject block26 = new GameObject(2600, 250 + DOWN_SHIFT, 50, 140, TERMINAL_VELOCITY);
	GameObject block27 = new GameObject(2700, 250 + DOWN_SHIFT, 50, 120, TERMINAL_VELOCITY);
	GameObject block28 = new GameObject(2800, 250 + DOWN_SHIFT, 50, 100, TERMINAL_VELOCITY);
	GameObject block29 = new GameObject(2900, 250 + DOWN_SHIFT, 50, 80, TERMINAL_VELOCITY);
	GameObject block30 = new GameObject(3000, 250 + DOWN_SHIFT, 50, 60, TERMINAL_VELOCITY);
	GameObject block31 = new GameObject(3100, 250 + DOWN_SHIFT, 50, 40, TERMINAL_VELOCITY);
	GameObject block32 = new GameObject(3200, 250 + DOWN_SHIFT, 50, 20, TERMINAL_VELOCITY);
	GameObject block33 = new GameObject(3300, 250 + DOWN_SHIFT, 200, 160, TERMINAL_VELOCITY);
	
	GameObject block34 = new GameObject(-270, -250 + DOWN_SHIFT, 60, 60, TERMINAL_VELOCITY);
	
	GameObject lava1 = new GameObject(-1000, 600 + DOWN_SHIFT, 2350, 40, TERMINAL_VELOCITY);
	GameObject lava2 = new GameObject(1403, 600 + DOWN_SHIFT, 5000, 40, TERMINAL_VELOCITY);
	
	
	PlayerObject mario = new PlayerObject(marioX, marioY - 200, marioW, marioH, TERMINAL_VELOCITY);
	
	PlayerObject luigi = new PlayerObject(marioX + 30, marioY - 200, 30, 30, TERMINAL_VELOCITY);
	
	Music sound = new Music(1);
	
	public MarioPanel(MarioFrame thisFrame) {
		
		
		
		game = thisFrame;
		//block8.setSticky(true);
		//block9.setSticky(true);
		//block10.setSticky(true);
		block11.setSticky(true);
		block13.setSticky(true);
		block15.setSticky(true);
		block16.setSticky(true);
		
		//block19.setSticky(true);
		//block20.setSticky(true);
		blocks.add(block1);
		blocks.add(block2);
		blocks.add(block3);
		blocks.add(block4);
		blocks.add(block5);
		blocks.add(block6);
		blocks.add(block7);
		blocks.add(block8);
		blocks.add(block9);
		blocks.add(block10);
		blocks.add(block11);
		blocks.add(block12);
		blocks.add(block13);
		blocks.add(block14);
		blocks.add(block15);
		blocks.add(block16);
		blocks.add(block17);
		blocks.add(block18);
		blocks.add(block19);
		blocks.add(block20);
		blocks.add(block21);
		blocks.add(block22);
		blocks.add(block23);
		blocks.add(block24);
		blocks.add(block25);
		blocks.add(block26);
		blocks.add(block27);
		blocks.add(block28);
		blocks.add(block29);
		blocks.add(block30);
		blocks.add(block31);
		blocks.add(block32);
		blocks.add(block33);
		blocks.add(block34);
		
		coins.add(coin1);
		coins.add(coin2);
		coins.add(coin3);
		coins.add(coin4);
		coins.add(coin5);
		coins.add(coin6);
		coins.add(coin7);
		coins.add(coin8);
		coins.add(coin9);
		coins.add(coin10);
		coins.add(coin11);
		coins.add(coin12);
		coins.add(coin13);
		coins.add(coin14);
		coins.add(coin15);
		coins.add(coin16);
		coins.add(coin17);
		coins.add(coin18);
		coins.add(coin19);
		coins.add(coin20);
		coins.add(coin21);
		coins.add(coin22);
		coins.add(coin23);
		coins.add(coin24);
		coins.add(coin25);
		coins.add(coin26);
		coins.add(coin27);
		coins.add(coin28);
		coins.add(coin29);
		coins.add(coin30);
		coins.add(coin31);
		coins.add(coin32);
		coins.add(coin33);
		coins.add(coin34);
		coins.add(coin35);
		coins.add(coin36);
		coins.add(coin37);
		coins.add(coin38);
		coins.add(coin39);
		coins.add(coin40);
		coins.add(coin41);
		coins.add(coin42);
		coins.add(coin43);
		coins.add(coin44);
		coins.add(coin45);
		coins.add(coin46);
		coins.add(coin47);
		coins.add(coin48);
		coins.add(coin49);
		coins.add(coin50);
		coins.add(coin51);
		coins.add(coin52);
		coins.add(coin53);
		coins.add(coin54);
		coins.add(coin55);
		coins.add(coin56);
		coins.add(coin57);
		
		objects.add(sign);
		objects.add(signPost);
		
		lava.add(lava1);
		lava.add(lava2);
		
		//mario.delete();
		
		MarioThread mt = new MarioThread();
		mt.start();
		
		LuigiThread lt = new LuigiThread();
		lt.start();
		
		//snakeButton.setIcon(snakeIcon);
		//chessButton.setIcon(chessIcon);
		//chessButton.setHorizontalAlignment(SwingConstants.CENTER);
		//System.out.println("here");
		this.setPreferredSize(new Dimension (SCREEN_WIDTH, SCREEN_HEIGHT));
		
		//this.setLayout(new GridLayout(1, buttonList.size(), 0, 0));
		this.setBackground(Color.BLACK);
		
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void jump(PlayerObject x) {
		//Rectangle m = new Rectangle(marioX, marioY, 30, 30);
		//Rectangle b1 = new Rectangle(block1X, block1Y, block1W, block1H);
		//Rectangle b1Frame = new Rectangle(block1X, block1Y - TERMINAL_VELOCITY, block1W, block1H + TERMINAL_VELOCITY);
		//Rectangle b2 = new Rectangle(block2.getX(), block2.getY(), block2.getW(), block2.getH());
		//Rectangle b2Frame = new Rectangle(block2.getX(), block2.getY() - TERMINAL_VELOCITY, block2.getW(), block2.getH() + TERMINAL_VELOCITY);
		velocity = 1;
		int count = 0;
		boolean firstJump = false;
		boolean isMario = false;
		if(x.equals(mario)) {
			isMario = true;
		}
		//System.out.println(isMario);
		if(x.covers(blocks)) {
			firstJump = true;
		}
		else {
			firstJump = false;
		}
		//checks if jump is allowed
		if(x.covers(blocks) || doubleJump) {
			new Music(6);
		while(!(x.overlaps(blocks)) && count < JUMP_HEIGHT) {
			if(x.getY() > (SCREEN_HEIGHT/8)) {
			x.setY(x.getY() - 1);
			//System.out.println(x.toString());
			}
			else {
				moveY(1);
				if(!isMario) {
					mario.setY(mario.getY() + 1);
				}
				else {
					luigi.setY(luigi.getY() + 1);
				}
				if(x.overlaps(blocks)) {
					moveY(-1);
					if(!isMario) {
						mario.setY(mario.getY() - 1);
						//System.out.println("mario going up bc luigi collides");
					}
					else {
						luigi.setY(luigi.getY() - 1);
						//System.out.println("luigi going up bc mario collides");
					}
				}
			}
			count++;
		}
		//jump was interrupted
		if(count < JUMP_HEIGHT) {
			x.setY(x.getY() + 1);
		}
		}
		if(firstJump) {
			doubleJump = true;
		}
		else {
			doubleJump = false;
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if((mario.covers(lava))) {
			mario.delete();
			int x = (SCREEN_WIDTH/2) - luigi.getX();
			int y = (SCREEN_HEIGHT/3) - luigi.getY();
			moveX(x);
			moveY(y);
			luigi.setX(SCREEN_WIDTH/2);
			luigi.setY(SCREEN_HEIGHT/3 - TERMINAL_VELOCITY);
			System.out.println(lava1.toString());
		}
		if((luigi.covers(lava))){
			luigi.delete();
			int x = (SCREEN_WIDTH/2) - mario.getX();
			int y = (SCREEN_HEIGHT/3) - mario.getY();
			moveX(x);
			moveY(y);
			mario.setX(SCREEN_WIDTH/2);
			mario.setY(SCREEN_HEIGHT/3 - TERMINAL_VELOCITY);
		}
		if((!(mario.wasDeleted()) || !(luigi.wasDeleted())) && mario.getScore() + luigi.getScore() < coins.size()
				&& mario.getX() < block33.getX() + 100 
				&& luigi.getX() < block33.getX() + 100 ) {
		//mario facing right image
			ImageIcon tempMarioIcon = new ImageIcon(this.getClass().getResource("mario5.png"));
			Image mImg = tempMarioIcon.getImage();
			Image newMR = mImg.getScaledInstance(mario.getW(), mario.getH(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon marioIcon = new ImageIcon(newMR);
		//mario facing left image
			ImageIcon tempMarioLIcon = new ImageIcon(this.getClass().getResource("mario6.png"));
			Image mLImg = tempMarioLIcon.getImage();
			Image newML = mLImg.getScaledInstance(mario.getW(), mario.getH(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon marioLIcon = new ImageIcon(newML);
		//luigi facing right image
			ImageIcon tempLuigiIcon = new ImageIcon(this.getClass().getResource("luigiRight.png"));
			Image lImg = tempLuigiIcon.getImage();
			Image newLR = lImg.getScaledInstance(luigi.getW(), luigi.getH(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon luigiIcon = new ImageIcon(newLR);
		//luigi facing left image
			ImageIcon tempLuigiLIcon = new ImageIcon(this.getClass().getResource("luigiLeft.png"));
			Image lLImg = tempLuigiLIcon.getImage();
			Image newLL = lLImg.getScaledInstance(luigi.getW(), luigi.getH(), java.awt.Image.SCALE_SMOOTH);
			ImageIcon luigiLIcon = new ImageIcon(newLL);
		//background image
			ImageIcon backIcon = new ImageIcon(this.getClass().getResource("background.png"));
			Image backImg = backIcon.getImage();
			Image background = backImg.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
			ImageIcon bIcon = new ImageIcon(background);
		//drawing background
			g.drawImage(background, 0, 0, null);
		//drawing mario
			if(mario.getRight() || !mario.getLeft()) {
				g.drawImage(newMR, mario.getX(), mario.getY(), null);
			}
			else g.drawImage(newML, mario.getX(), mario.getY(), null);
		//drawing luigi
			if(luigi.getRight() || !luigi.getLeft()) {
				g.drawImage(newLR, luigi.getX(), luigi.getY(), null);
			}
			else g.drawImage(newLL, luigi.getX(), luigi.getY(), null);
		//drawing brown block
			g.setColor(new Color(125, 60, 0));
			g.fillRect(block1.getX(), block1.getY(), block1.getW(), block1.getH());
		//drawing light green block
			g.setColor(Color.green);
			g.fillRect(block2.getX(), block2.getY(), block2.getW(), block2.getH());
		//drawing brown block
			g.setColor(new Color(125, 60, 0));
			g.fillRect(block3.getX(), block3.getY(), block3.getW(), block3.getH());
		//drawing light green blocks (4-7)
			g.setColor(Color.green);
			for(int i = 3; i < 7; i++) {
				g.fillRect(blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getW(), blocks.get(i).getH());
			}
		//drawing silver climbing blocks
			g.setColor(new Color (160, 160, 160));
			g.fillRect(block8.getX(), block8.getY(), block8.getW(), block8.getH());
			g.fillRect(block9.getX(), block9.getY(), block9.getW(), block9.getH());
			g.fillRect(block10.getX(), block10.getY(), block10.getW(), block10.getH());
			g.fillRect(block11.getX(), block11.getY(), block11.getW(), block11.getH());
		//drawing light green block
			g.setColor(Color.green);
			g.fillRect(block12.getX(), block12.getY(), block12.getW(), block12.getH());
		//drawing dark green vine
			g.setColor(new Color(1, 100, 1));
			g.fillRect(block13.getX(), block13.getY(), block13.getW(), block13.getH());
		//drawing light green block
			g.setColor(Color.green);
			g.fillRect(block14.getX(), block14.getY(), block14.getW(), block14.getH());
		//coloring dark green blocks (15-22)
			g.setColor(new Color(1, 120, 1));
			for(int i = 14; i < 22; i++) {
				g.fillRect(blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getW(), blocks.get(i).getH());
			}
		//coloring light green blocks (23-33)
			g.setColor(Color.green);
			for(int i = 22; i < 33; i++) {
				g.fillRect(blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getW(), blocks.get(i).getH());
			}
		//drawing easter egg
			ImageIcon scaryIcon = new ImageIcon(this.getClass().getResource("scary.jpg"));
			Image scaryImg = scaryIcon.getImage();
			Image scary = scaryImg.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
			ImageIcon scryIcon = new ImageIcon(scary);
			g.drawImage(scary, block34.getX(), block34.getY(), null);
		//drawing all coins
			g.setColor(new Color(255, 215, 1));
			for(int i = 0; i < coins.size(); i++) {
				g.fillOval(coins.get(i).getX(), coins.get(i).getY(), coins.get(i).getW(), coins.get(i).getH());
			}
		//labeling all blocks
			/*g.setColor(Color.white);
			g.setFont(new Font("Ink Free", Font.BOLD, 15));
			for(int i = 0; i < blocks.size(); i++) {
				g.drawString("BLOCK " + (i + 1), blocks.get(i).getX(), blocks.get(i).getY());
			}*/
		//labeling all coins
			/*g.setFont(new Font("Ink Free", Font.BOLD, 5));
			for(int i = 0; i < coins.size(); i++) {
				g.drawString("COIN " + (i + 1), coins.get(i).getX(), coins.get(i).getY());
			}*/
		//drawing sign
			g.setColor(new Color(165, 42, 42));
			g.fillRect(sign.getX(), sign.getY() - 20, sign.getW(), sign.getH() + 20);
			g.fillRect(signPost.getX(), signPost.getY(), signPost.getW(), signPost.getH());
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free", Font.BOLD, 10));
			g.drawString("Try going backwards", sign.getX() + 5, sign.getY() + 18);
			g.drawString("Missing Coins?", sign.getX() + 20, sign.getY() );
		//color changing for lava
			if(grnVal <= 5) {
				grnChange*=-1;
			}
			if(grnVal >= 60) {
				grnChange*=-1;
			}
			grnVal = grnVal + grnChange;
		//drawing lava
			g.setColor(new Color(250, grnVal, 0));
			for(int i = 0; i < lava.size(); i++) {
				g.fillRect(lava.get(i).getX(), lava.get(i).getY(), lava.get(i).getW(), lava.get(i).getH());
			}
		//drawing door toward the end of the world
			g.setColor(new Color(75, 0, 130));
			g.fillRect(block33.getX() + 100, block33.getY() - 60, 40, 60);
		//drawing score keeper
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free", Font.BOLD, 25));
			g.drawString("" + (mario.getScore() + luigi.getScore()), 24, 22);
			g.setColor(new Color(255, 210, 10));
			g.fillOval(4, 2, 16, 20);
			
		}
		else if(mario.getScore() + luigi.getScore() == coins.size() || mario.getX() >= block33.getX() + 100
				|| luigi.getX() >= block33.getX() + 100 ) {
			gameWon(g);
		}
		else if(mario.getScore() + luigi.getScore() != coins.size()) {
			gameOver(g);
		}
	}

	public void gameWon(Graphics g) {
		gameOver = true;
		sound.halt();
		new Music(3);
		//new Music(4);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(Color.orange);
		g.setFont(new Font("Ink Free", Font.BOLD, 85));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("YOU WIN", 
				(SCREEN_WIDTH - metrics1.stringWidth("YOU WIN"))/2, 175);
		//try {
		//	Thread.sleep(1000);
		//} catch (InterruptedException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
	
	}
	
	public void gameOver(Graphics g) {
		gameOver = true;
		//System.out.println("Game Ended");
		sound.halt();
		new Music(2);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setColor(new Color(150, 0, 140));
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", 
				(SCREEN_WIDTH - metrics1.stringWidth("GAME OVER"))/2, 150);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!gameOver) {
		repaint();
		}
		//System.out.println("mario bound check" + (mario.getX() > block33.getX() + 100));
		//System.out.println("luigi bound check" + (luigi.getX() > block33.getX() + 100));
		//System.out.println("is mario deleted? " + mario.wasDeleted());
		/*
		if(mario.box().intersects(block12.hitBox())) {
			mario.delete();
			int x = (SCREEN_WIDTH/2) - luigi.getX();
			int y = (SCREEN_HEIGHT/3) - luigi.getY();
			moveCoinsX(x);
			moveCoinsY(y);
			moveBlocksX(x);
			moveBlocksY(y);
			moveObjX(x);
			moveObjY(y);
			luigi.setX(SCREEN_WIDTH/2);
			luigi.setY(SCREEN_HEIGHT/3);
		}
		if(luigi.box().intersects(block12.hitBox())) {
			luigi.delete();
			int x = (SCREEN_WIDTH/2) - mario.getX();
			int y = (SCREEN_HEIGHT/3) - mario.getY();
			moveCoinsX(x);
			moveCoinsY(y);
			moveBlocksX(x);
			moveBlocksY(y);
			moveObjX(x);
			moveObjY(y);
			mario.setX(SCREEN_WIDTH/2);
			mario.setY(SCREEN_HEIGHT/3);
		}
		*/
	}
	
	public void reset(Rectangle m) {
		m = new Rectangle(marioX, marioY, 30, 30);
	}
	
	public void moveBlocksX(int n) {
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setX(blocks.get(i).getX() + n);
		}
	}
	
	public void moveBlocksY(int n) {
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setY(blocks.get(i).getY() + n);
		}
	}
	
	public void moveCoinsX(int n) {
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).setX(coins.get(i).getX() + n);
		}
	}
	
	public void moveCoinsY(int n) {
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).setY(coins.get(i).getY() + n);
		}
	}
	
	public void moveObjX(int n) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).setX(objects.get(i).getX() + n);
		}
	}
	
	public void moveObjY(int n) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).setY(objects.get(i).getY() + n);
		}
	}
	
	public void moveLavaX(int n) {
		for(int i = 0; i < lava.size(); i++) {
			lava.get(i).setX(lava.get(i).getX() + n);
		}
	}
	
	public void moveLavaY(int n) {
		for(int i = 0; i < lava.size(); i++) {
			lava.get(i).setY(lava.get(i).getY() + n);
		}
	}
	
	public void moveX(int n) {
		moveBlocksX(n);
		moveCoinsX(n);
		moveObjX(n);
		moveLavaX(n);
	}
	
	public void moveY(int n) {
		moveBlocksY(n);
		moveCoinsY(n);
		moveObjY(n);
		moveLavaY(n);
	}
	
	//public boolean gravity() {
		
	//	return true;
	//}
	
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
			//case KeyEvent.VK_W:
			//	leftUp = true;
			//	break;
			case KeyEvent.VK_UP:
				jump(mario);
				break;
			case KeyEvent.VK_DOWN:
				int x = (SCREEN_WIDTH/2) - mario.getX();
				int y = (SCREEN_HEIGHT/3) - mario.getY();
				moveX(x);
				moveY(y);
				mario.setX(SCREEN_WIDTH/2);
				mario.setY(SCREEN_HEIGHT/3 - 30);
				luigi.setX(luigi.getX() + x);
				luigi.setY(luigi.getY() + y);
				break;
			case KeyEvent.VK_LEFT:
				mario.setLeft(true);
				//left = true;
				//System.out.println("left button pressed");
				break;
			case KeyEvent.VK_RIGHT:
				mario.setRight(true);
				//right = true;
				break;
			case KeyEvent.VK_W:
				jump(luigi);
				break;
			case KeyEvent.VK_S:
				x = (SCREEN_WIDTH/2) - luigi.getX();
				y = (SCREEN_HEIGHT/3) - luigi.getY();
				moveX(x);
				moveY(y);
				luigi.setX(SCREEN_WIDTH/2);
				luigi.setY(SCREEN_HEIGHT/3 - 30);
				mario.setX(mario.getX() + x);
				mario.setY(mario.getY() + y);
				break;
			case KeyEvent.VK_A:
				luigi.setLeft(true);
				//System.out.println("Luigi going left " + luigi.getLeft());
				break;
			case KeyEvent.VK_D:
				luigi.setRight(true);
				//System.out.println("Luigi going right " + luigi.getRight());
				break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				mario.setLeft(false);
				//left = false;
				//System.out.println("UP Key Released");
				break;
			case KeyEvent.VK_RIGHT:
				mario.setRight(false);
				//right = false;
				//System.out.println("DOWN Key Released");
				break;
			case KeyEvent.VK_A:
				luigi.setLeft(false);
				//System.out.println("Luigi going left " + luigi.getLeft());
				break;
			case KeyEvent.VK_D:
				luigi.setRight(false);
				//System.out.println("Luigi going right " + luigi.getRight());
				break;
			}
		}
		
	}

	

	public class MarioThread extends Thread{
		
		private boolean keepGoing = true;
		
		MarioThread(){
			
		}
		
		public void halt(){
			keepGoing = false;
		}
		
		public void run() {
			
			int counter = 0;
			while(keepGoing) {
				//System.out.println("getting in mario thread loop");
				//System.out.println(mario.toString());
				//System.out.println(mario.overlaps(block2, block1));
				//Rectangle m = new Rectangle(marioX, marioY, 30, 30);
				//Rectangle b1 = new Rectangle(block1X, block1Y, block1W, block1H);
				//Rectangle b1Frame = new Rectangle(block1X, block1Y - TERMINAL_VELOCITY, block1W, block1H + TERMINAL_VELOCITY);
				//Rectangle b2 = block2.hitBox();
				//Rectangle b2Frame = new Rectangle(block2.getX(), block2.getY() - TERMINAL_VELOCITY, block2.getW(), block2.getH() + TERMINAL_VELOCITY);
				//reset(m);
				//System.out.println(m.intersects(box1));
				//gravity
				if(mario.getY() < (3 * SCREEN_HEIGHT/4) + DOWN_SHIFT && mario.gravity(blocks) && counter % 2 == 1){//!m.intersects(b1Frame) && !m.intersects(b2Frame) && counter % 2 == 1) {
					velocity++;
					if(velocity > TERMINAL_VELOCITY) {
						velocity = TERMINAL_VELOCITY;
					}
					//marioY = marioY + velocity;
					mario.setY(mario.getY() + velocity);
					//System.out.println("gravity " + mario.gravity(block1, block2));
					//System.out.println("box1Frame" + m.intersects(b1Frame));
					//reset(m);
				}
				else if(mario.gravity(blocks) && counter % 2 == 1) {
					velocity++;
					if(velocity > TERMINAL_VELOCITY) {
						velocity = TERMINAL_VELOCITY;
					}
					moveY(-velocity);
					luigi.setY(luigi.getY() - velocity);
				}
				if(mario.holds(coins) != null) {
					//System.out.println(mario.holds(coins).toString());
					new Music(5);
					mario.holds(coins).deleteCoin();
					coins.remove(mario.holds(coins));
					mario.addCoin();
				}
				//right movement
				//System.out.println("getting in mario thread loop");
				//System.out.println("Current check if mario is within right border: " + (mario.getX() < 7 * (SCREEN_WIDTH/8)));
				if(mario.getRight() && mario.getX() < 7 * (SCREEN_WIDTH/8) && !mario.overlaps(blocks)) {//!m.intersects(b1) && !m.intersects(b2)) {
					mario.setX(mario.getX() + 2);
					//marioX+=2;
					//marioX+=2;
					//reset(m);
					//System.out.println("M: " + m.toString());
					//System.out.println("box 1: " + box1.toString());
					//System.out.println("box 2: " + block2.toString());
					//Rectangle test1 = new Rectangle(20, 20, 20 , 20);
					//Rectangle test2 = new Rectangle(0, 20, 20 , 20);
					//System.out.println("Test" + test1.intersects(test2));
					if(mario.overlaps(blocks)) {
						//System.out.println("Quick buffered to the left");
						if(mario.touchingSticky(blocks)) {
							//System.out.println("touching sticky " + mario.touchingSticky(blocks));
							if(mario.getY() > SCREEN_HEIGHT/8) {
							mario.setY(mario.getY() - 3);
							}
							else {
								moveY(3);
								luigi.setY(luigi.getY() + 3);
							}
						}
						mario.setX(mario.getX() - 2);
						//marioX-=2;
						//reset(m);
					}
				}
				//scrolling for right side
				else if(mario.getRight() && mario.getX() >= 7 * (SCREEN_WIDTH/8)) {
					moveX(-2);
					luigi.setX(luigi.getX() - 2);
					//block1.setX(block1.getX() - 2);
					//block2.setX(block2.getX() - 2);
					//block3.setX(block3.getX() - 2);
					//bouncing mario back from intersected walls
					if(mario.overlaps(blocks)) {
						//System.out.println("Buffered to the left");
						mario.setX(mario.getX() - 2);
						//reset(m);
					}
				}
				
				//left movement
				if(mario.getLeft() && mario.getX() > SCREEN_WIDTH/8 && !mario.overlaps(blocks)) {
					//System.out.println("Going left");
					mario.setX(mario.getX() - 2);
					//reset(m);
					if(mario.overlaps(blocks)) {
						//System.out.println(mario.touchingSticky(blocks));
						if(mario.touchingSticky(blocks)) {
							if(mario.getY() > SCREEN_HEIGHT/8) {
							mario.setY(mario.getY() - 3);
							}
							else {
								moveY(3);
								luigi.setY(luigi.getY() + 3);
							}
						}
						mario.setX(mario.getX() + 2);
						//reset(m);
					}
				}
				//scrolling for left side
				else if(mario.getLeft() && mario.getX() <= SCREEN_WIDTH/8) {
					moveX(2);
					luigi.setX(luigi.getX() + 2);
					//bouncing mario back from intersected walls
					if(mario.overlaps(blocks)) {
						//System.out.println("Buffered to the right");
						mario.setX(mario.getX() + 2);
						//reset(m);
					}
				}
				
				//if(m.intersects(box1) || m.intersects(block2)) {
				//	System.out.println("Buffered to the right");
				//	marioX+=5;
				//}
				/*
				if(m.intersects(box1Frame) && m.intersects(block2Frame)) {
					if(marioX < blockX && marioY + marioH - TERMINAL_VELOCITY > blockY && marioY < blockY + blockH) {
						marioX-=1;
					}
					else if(marioX >= blockX && marioY + marioH - TERMINAL_VELOCITY > blockY && marioY < blockY + blockH) {
						marioX+=1;
					}		
				}
				*/
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counter++;
				//System.out.println("In Thread Loop");
			}
		}
		
	}
	
//}

public class LuigiThread extends Thread{
	
	private boolean keepGoing = true;
	
	LuigiThread(){
		
	}
	
	public void halt(){
		keepGoing = false;
	}
	
	public void run() {
		
		int counter = 0;
		while(keepGoing) {
			//System.out.println(mario.toString());
			//System.out.println(mario.overlaps(block2, block1));
			//Rectangle m = new Rectangle(marioX, marioY, 30, 30);
			//Rectangle b1 = new Rectangle(block1X, block1Y, block1W, block1H);
			//Rectangle b1Frame = new Rectangle(block1X, block1Y - TERMINAL_VELOCITY, block1W, block1H + TERMINAL_VELOCITY);
			//Rectangle b2 = block2.hitBox();
			//Rectangle b2Frame = new Rectangle(block2.getX(), block2.getY() - TERMINAL_VELOCITY, block2.getW(), block2.getH() + TERMINAL_VELOCITY);
			//reset(m);
			//System.out.println(m.intersects(box1));
			//gravity
			if(luigi.getY() < (3 * SCREEN_HEIGHT/4) + DOWN_SHIFT && luigi.gravity(blocks) && counter % 2 == 1){//!m.intersects(b1Frame) && !m.intersects(b2Frame) && counter % 2 == 1) {
				velocity++;
				if(velocity > TERMINAL_VELOCITY) {
					velocity = TERMINAL_VELOCITY;
				}
				//marioY = marioY + velocity;
				luigi.setY(luigi.getY() + velocity);
				//System.out.println("gravity " + mario.gravity(block1, block2));
				//System.out.println("box1Frame" + m.intersects(b1Frame));
				//reset(m);
			}
			else if(luigi.gravity(blocks) && counter % 2 == 1) {
				velocity++;
				if(velocity > TERMINAL_VELOCITY) {
					velocity = TERMINAL_VELOCITY;
				}
				moveY(-velocity);
				mario.setY(mario.getY() - velocity);
			}
			if(luigi.holds(coins) != null) {
				//System.out.println(luigi.holds(coins).toString());
				new Music(5);
				luigi.holds(coins).deleteCoin();
				coins.remove(luigi.holds(coins));
				luigi.addCoin();
			}
			//right movement
			if(luigi.getRight() && luigi.getX() < 7 * (SCREEN_WIDTH/8) && !luigi.overlaps(blocks)) {//!m.intersects(b1) && !m.intersects(b2)) {
				luigi.setX(luigi.getX() + 2);
				//System.out.println("luigi going right");
				//marioX+=2;
				//marioX+=2;
				//reset(m);
				//System.out.println("M: " + m.toString());
				//System.out.println("box 1: " + box1.toString());
				//System.out.println("box 2: " + block2.toString());
				//Rectangle test1 = new Rectangle(20, 20, 20 , 20);
				//Rectangle test2 = new Rectangle(0, 20, 20 , 20);
				//System.out.println("Test" + test1.intersects(test2));
				if(luigi.overlaps(blocks)) {
					//System.out.println("Quick buffered to the left");
					if(luigi.touchingSticky(blocks)) {
						if(luigi.getY() > SCREEN_HEIGHT/8) {
						luigi.setY(luigi.getY() - 3);
						}
						else {
							moveY(3);
							mario.setY(mario.getY() + 3);
						}
					}
					luigi.setX(luigi.getX() - 2);
					//marioX-=2;
					//reset(m);
				}
			}
			//scrolling for right side
			else if(luigi.getRight() && luigi.getX() >= 7 * (SCREEN_WIDTH/8)) {
				moveX(-2);
				mario.setX(mario.getX() - 2);
				//block1.setX(block1.getX() - 2);
				//block2.setX(block2.getX() - 2);
				//block3.setX(block3.getX() - 2);
				//bouncing mario back from intersected walls
				if(luigi.overlaps(blocks)) {
					//System.out.println("Buffered to the left");
					luigi.setX(luigi.getX() - 2);
					//reset(m);
				}
			}
			
			//left movement
			if(luigi.getLeft() && luigi.getX() > SCREEN_WIDTH/8 && !luigi.overlaps(blocks)) {
				//System.out.println("Going left");
				luigi.setX(luigi.getX() - 2);
				//System.out.println("luigi: " + luigi.toString());
				//reset(m);
				if(luigi.overlaps(blocks)) {
					//System.out.println(mario.touchingSticky(blocks));
					if(luigi.touchingSticky(blocks)) {
						if(luigi.getY() > SCREEN_HEIGHT/8) {
						luigi.setY(luigi.getY() - 3);
						}
						else {
							moveY(3);
							mario.setY(mario.getY() + 3);
						}
					}
					luigi.setX(luigi.getX() + 2);
					//reset(m);
				}
			}
			//scrolling for left side
			else if(luigi.getLeft() && luigi.getX() <= SCREEN_WIDTH/8) {
				moveX(2);
				mario.setX(mario.getX() + 2);
				//bouncing mario back from intersected walls
				if(luigi.overlaps(blocks)) {
					//System.out.println("Buffered to the right");
					luigi.setX(luigi.getX() + 2);
					//reset(m);
				}
			}
			
			//if(m.intersects(box1) || m.intersects(block2)) {
			//	System.out.println("Buffered to the right");
			//	marioX+=5;
			//}
			/*
			if(m.intersects(box1Frame) && m.intersects(block2Frame)) {
				if(marioX < blockX && marioY + marioH - TERMINAL_VELOCITY > blockY && marioY < blockY + blockH) {
					marioX-=1;
				}
				else if(marioX >= blockX && marioY + marioH - TERMINAL_VELOCITY > blockY && marioY < blockY + blockH) {
					marioX+=1;
				}		
			}
			*/
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			//System.out.println("In Thread Loop");
		}
	}
	
}

}
