import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class car extends movingObjects implements Runnable {
    private int x, y, width, height;
    private boolean moving;
    private JLabel carLabel;
    private int direction;

    public car(int x, int y, int width, int height, String image, int direction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        moving = false;

        carLabel = new JLabel();
        ImageIcon carImageIcon = new ImageIcon(getClass().getResource("sprites/" + image));
        carLabel.setIcon(carImageIcon);
        carLabel.setSize(width, height);
        carLabel.setLocation(x, y);
    }

    public JLabel getCarLabel() {
        return carLabel;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Boolean getMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }
    
    public void startThread() {
//        Thread t = new Thread(this);
//        t.start();
    }

    //left run
    @Override
    public void run() {
//        while (moving) {
//            if (direction == 0) {
//                moveLeft();
//            } else {
//                moveRight();
//            }
//            try {
//                Thread.sleep(600);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void moveLeft() {
        
//        x -= gameProperties.CAR_STEP;
//
//        if (x + width < 0) {
//            x = gameProperties.SCREEN_WIDTH;
//        }
//
//        carLabel.setLocation(x, y);
    }

    private void moveRight() {
    	
//        x += gameProperties.CAR_STEP;
//
//        if (x >= gameProperties.SCREEN_WIDTH) {
//            x = -width;
//        }
//        carLabel.setLocation(x, y);
    }
}