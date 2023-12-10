import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class movingObjects extends spriteObjects{
	private Boolean visible, moving;
	private Thread t;
	private JLabel carLabel;
	private JLabel logLabel;
	private JLabel froggerLabel;
	private frogger frogger;
	private log log;
	private car car;
	private JButton startButton;

	
	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public movingObjects() {
		super();
	}
	
	public movingObjects(Boolean visible, Boolean moving) {
		super();
		this.moving = moving;
		this.visible = visible;
	}
	
	public movingObjects(Boolean visible, Boolean moving, int x, int y, int height, int width, String image) {
		super(x, y, height, width, image);
		this.moving = moving;
		this.visible = visible;
	}

	
	//public void run() {

//		while (this.moving) {
//			// moving code goes here
//			int x = this.x;
//
//			// increase x
//			x += gameProperties.FROGGER_STEP;
//
//			// boundary check (loop around to other side of screen)
//			if (x >= gameProperties.SCREEN_WIDTH) {
//				x = -1 * this.width;
//			}
//
//			this.x = x;
//			
//			this.setX(x); // this is required to update rectangle movement
//			carLabel.setLocation(this.x, this.y);
//			
//			// detect collision
//			if (this.visible) {
//				this.detectCollision();
//			}
//				
//			
//			try {
//				Thread.sleep(200);
//			} catch (Exception e) {
//				e.printStackTrace(); // shows problem in console
//			}
//		} 
	//}
	
	
	//start thread 
	//public void startThread() {
//		if( !this.moving) {
//			this.moving = true;
//			t = new Thread(this, "Moving Objects Thread");
//			t.start();	
//		}
		
	//}
	
	//stop thread
	//public void stopThread() {
//		this.moving = false;
	//}

	// method to pass the original JLabel for car
	// into the class so we can effect the original label

//	public void setcarLabel(JLabel temp) {
//		carLabel = temp;
//	}
	
//	public void setLogLabel(JLabel temp) {
//	logLabel = temp;
//}

	public void setFroggerLabel(JLabel temp) {
		froggerLabel = temp;
	}
	
	public void setStartButton(JButton temp) {
		startButton = temp;
	}

	public void setFrogger(frogger temp) {
		frogger = temp;
	}

//	public void setLog(log temp) {
//		log = temp;
//	}

	//private void detectCollision() {
//		if (hBox.intersects(frogger.getRectangle())) {
//			System.out.println("boom!");
//			this.moving = false;
//
//			this.froggerLabel.setIcon(new ImageIcon(getClass().getResource("sprites/frogger.png")));
//			this.carLabel.setIcon(new ImageIcon(getClass().getResource("sprites/carLeft.png")));
//		}
//	}
}

