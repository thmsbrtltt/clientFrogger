import java.awt.Rectangle;

public class spriteObjects {
	protected int x, y; //upper left, top positions
	protected int height, width;
	protected String image;
	protected Rectangle hBox;
	
	public spriteObjects() {
		super();
		hBox = new Rectangle(0,0,0,0);
	} 
	
	public spriteObjects(int x, int y, int height, int width, String image) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.image = image;
		hBox = new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		this.hBox.x = this.x; //moves rectangle with object on screen
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		this.hBox.y = this.y; //moves rectangle with object on screen
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Rectangle getRectangle() {
		return this.hBox;
	}
	
}

