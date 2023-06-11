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
	
	public void setSticky(boolean s) {
		sticky = s;
	}
	
	public boolean getSticky() {
		return sticky;
	}
	
	
	
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
