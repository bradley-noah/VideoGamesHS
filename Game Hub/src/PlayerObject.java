/*
 * Class for holding a PlayerObject.
 * It contains a hitbox as well as coordinates and other characteristics inherited from GameObject
 * It helps for player collision with all other GameObjects
 */
import java.awt.Rectangle;
import java.util.ArrayList;

public class PlayerObject extends GameObject{

	private int xVal;
	private int yVal;
	private int height;
	private int width;
	//private boolean isVisible = true;
	private int tVel;
	//private final int acceleration = 10;
	private int score = 0;
	private volatile boolean left = false;
	private volatile boolean right = false;
	private boolean deleted = false;
	
	public boolean wasDeleted() {
		return deleted;
	}
	public boolean getLeft() {
		return left;
	}
	public void setLeft(boolean l) {
		left = l;
	}
	public boolean getRight() {
		return right;
	}
	public void setRight(boolean r) {
		right = r;
	}
/**
 * 	method for returning the value for the x coordinate of a game object
 * 	@return x coordinate (top left of hitbox)	
 */
	public int getX() {
		return xVal;
	}
/**
 * 	method for setting the value for the x coordinate of a game object	
 */
	public void setX(int x) {
		xVal = x;
	}
/**
 * 	method for returning the value for the y coordinate of a game object	
 * 	@return y coordinate (top left of hitbox)
 */
	public int getY() {
		return yVal;
	}
/**
 * 	method for setting the value for the y coordinate of a game object	
 */
	public void setY(int y) {
		yVal = y;
	}

	public int getH() {
		return height;
	}

	public void setH(int h) {
		height = h;
	}

	public int getW() {
		return width;
	}

	public void setW(int w) {
		width = w;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	public void addCoin() {
		score++;
	}
	
	public void delete() {
		deleted = true;
		xVal = -10000;
		yVal = -10000;
		width = 1;
		height = 1;
	}

	public Rectangle box() {
		Rectangle box = new Rectangle(xVal, yVal, width, height);
		return box;
	}
	
	public Rectangle hitBox() {
		Rectangle box = new Rectangle(xVal, yVal - tVel, width, height + tVel);
		return box;
	}
	
	public boolean gravity(GameObject x, GameObject y) {
		Rectangle rect1 = x.hitBox();
		Rectangle rect2 = y.hitBox();
		//System.out.println(xVal);
		if(rect1.intersects(this.box())) {
			return false;
		}
		else if(rect2.intersects(this.box())) {
			return false;
		}
		return true;
	}
	
	public boolean gravity(ArrayList<GameObject> x) {
		if(deleted) {
			return false;
		}
		for(int i = 0; i < x.size(); i++) {
			Rectangle hitBox = x.get(i).hitBox();
			//System.out.println(hitBox);
			//System.out.println(this.box().intersects(hitBox));
			if(this.box().intersects(hitBox)) {
				//System.out.println("Intersects");
				return false;
			}
		}
		return true;
	}
	
	public GameObject holds(ArrayList<GameObject> x) {
		for(int i = 0; i < x.size(); i++) {
			if(this.box().intersects(x.get(i).box())) {
				return x.get(i);
			}
		}
		return null;
	}
	
	public boolean overlaps(ArrayList<GameObject> x) {
		for(int i = 0; i < x.size(); i++) {
			if(this.box().intersects(x.get(i).box())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean covers(ArrayList<GameObject> x) {
		for(int i = 0; i < x.size(); i++) {
			if(this.box().intersects(x.get(i).hitBox())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean touchingSticky(ArrayList<GameObject> x) {
		for(int i = 0; i < x.size(); i++) {
			if(this.box().intersects(x.get(i).box())) {
				return x.get(i).getSticky();
			}
		}
		return false;
	}
	
	public String toString() {
		return "(" + xVal + "," + yVal + ")  width: " + width + " height: " + height;
	}
	
	
	public PlayerObject(int x, int y, int w, int h, int t) {
		super(x, y, w, h, t);
		xVal = x;
		yVal = y;
		width = w;
		height = h;
		tVel = t;
	}
	
	
	

}
