import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class log extends movingObjects{
    private int x, y, width, height;
    private boolean moving;
    private JLabel logLabel;
    private int direction;

    public log(int x, int y, int width, int height, String image, int direction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        moving = false;

        logLabel = new JLabel();
        ImageIcon logImageIcon = new ImageIcon(getClass().getResource("sprites/" + image));
        logLabel.setIcon(logImageIcon);
        logLabel.setSize(width, height);
        logLabel.setLocation(x, y);
    }

    public JLabel getLogLabel() {
        return logLabel;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Boolean getMoving() {
        return moving;
    }

    public void startThread() {
//        Thread t = new Thread(this);
//        t.start();
    }

//    @Override
    //public void run() {
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
    //}

//    private void moveLeft() {
//        
//        x -= gameProperties.LOG_STEP;
//
//        if (x + width < 0) {
//            x = gameProperties.SCREEN_WIDTH;
//        }
//
//        logLabel.setLocation(x, y);
//    }

//    private void moveRight() {
    	
//        x += gameProperties.LOG_STEP;
//
//        if (x >= gameProperties.SCREEN_WIDTH) {
//            x = -width;
//        }
//
//        logLabel.setLocation(x, y);
//    }
}