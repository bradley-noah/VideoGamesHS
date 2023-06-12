/*
 * Class for holding a general GameObject, this can be a wall, pipe, ground,
 * and more. It contains a hitbox as well as coordinates and other characteristics
 * You can set game object's size, location, and stickiness with the methods below
 */
import java.awt.Rectangle;

public class GameObject {

	private int xVal;
	private int yVal;
	private int height;
	private int width;
	//private boolean isVisible = true;
	private int tVel;
	//private final int acceleration = 10;
	private boolean sticky = false;
	//private boolean coin = false;
/**
 * 	method for returning the value for the x coordinate of a game object
 * 	@return x coordinate (top left of hitbox)	
 */
	public int getX() {
		return xVal;
	}
/**
 * 	method for setting the value for the x coordinate of a game object	
 *  @param int x
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
 *  @param int y
 */
	public void setY(int y) {
		yVal = y;
	}
/**
 * 	method for returning the value for the height of a game object	
 * 	@return height
 */
	public int getH() {
		return height;
	}
/**
 * 	method for setting the value for the height of a game object
 *  @param int h	
 */
	public void setH(int h) {
		height = h;
	}
/**
 * 	method for returning the value for the width of a game object	
 * 	@return width
 */
	public int getW() {
		return width;
	}
/**
 * 	method for setting the value for the width of a game object	
 *  @param int w
 */
	public void setW(int w) {
		width = w;
	}
/**
 * 	method for setting the boolean value for whether the game object is sticky
 *  @param boolean s
 */	
	public void setSticky(boolean s) {
		sticky = s;
	}
/**
 * 	method for returning the boolean value for whether the game object is sticky	
 * 	@return sticky
 */	
	public boolean getSticky() {
		return sticky;
	}
/**
 * void method that moves a coin to a far off location, in essence, deleting it
 */
	public void deleteCoin() {
		xVal = 10000;
		yVal = 10000;
		width = 0;
		height = 0;
	}

	public Rectangle box() {
		Rectangle box = new Rectangle(xVal, yVal, width, height);
		return box;
	}
	
	public Rectangle hitBox() {
		Rectangle box = new Rectangle(xVal, yVal - tVel, width, height + tVel);
		return box;
	}
	
	public String toString() {
		return "(" + xVal + "," + yVal + ")  width: " + width + " height: " + height;
	}
	
	public GameObject(int x, int y, int w, int h, int t) {
		xVal = x;
		yVal = y;
		width = w;
		height = h;
		tVel = t;
	}
	
	
	
}
