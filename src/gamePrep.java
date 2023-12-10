import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class gamePrep extends JFrame implements KeyListener, ActionListener {

	final static int CLIENT_PORT = 5656;
	final static int SERVER_PORT = 5556;
   
    private frogger frogger1;
    private frogger frogger2;
    private frogger currentFrogger;
    private car[][] cars;
    private log[][] logs;

    // gui variables
    private Container content;
    private JLabel froggerLabel;
    private ImageIcon froggerImage;

    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    private JLabel instructLabel;

    // buttons
    private JButton startButton;
    private JButton restartButton;

    private boolean gameStarted = false;
    
    private int currentPlayer = 1;

    private void carLogInit() {

        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars[i].length; j++) {
                cars[i][j] = new car(40 + j * 150, 255 + i * 60, 150, 150, "carLeft.png", (i % 2 == 0) ? 0 : 1);
                add(cars[i][j].getCarLabel());
            }
        }

        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[i].length; j++) {
                logs[i][j] = new log(40 + j * 150, 60 + i * 60, 150, 150, "log.png", (i % 2 == 0) ? 0 : 1);
                add(logs[i][j].getLogLabel());
            }
        }
    }

    private void winCondition() {
        gameStarted = false;
        System.out.println("you made it! press Restart to play again");
    }

    private void resetObjects() {
        updateFroggerPosition(currentFrogger, 275, 400);
        stopCarsAndLogs();
        resetCarsAndLogs();
    }

    private void stopCarsAndLogs() {
        for (car[] carRow : cars) {
            for (car car : carRow) {
                car.setMoving(false);
            }
        }

        for (log[] logRow : logs) {
            for (log log : logRow) {
                log.setMoving(false);
            }
        }
    }

    private void resetCarsAndLogs() {
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars[i].length; j++) {
                int initialX = 40 + j * 150;
                int initialY = 255 + i * 60;
                cars[i][j].getCarLabel().setLocation(initialX, initialY);
            }
        }

        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[i].length; j++) {
                int initialX = 40 + j * 150;
                int initialY = 60 + i * 60;
                logs[i][j].getLogLabel().setLocation(initialX, initialY);
            }
        }
    }

    private void startCarsAndLogs() {
        for (car[] carRow : cars) {
            for (car car : carRow) {
                car.setMoving(true);
                car.startThread();
            }
        }

        for (log[] logRow : logs) {
            for (log log : logRow) {
                log.setMoving(true);
                log.startThread();
            }
        }
    }

    private void updateFroggerPosition(frogger frogger, int x, int y) {
        frogger.setX(x);
        frogger.setY(y);
        froggerLabel.setLocation(frogger.getX(), frogger.getY());
    }

    public gamePrep() {
        frogger1 = new frogger(100, 200, 96, 96, "frogger.png");
        frogger2 = new frogger(200, 200, 96, 96, "frogger.png");
        currentFrogger = frogger1;
        cars = new car[3][4];
        logs = new log[3][4];

        backgroundImage = new ImageIcon(getClass().getResource("sprites/background.png"));

        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT);
        setSize(gameProperties.SCREEN_WIDTH, gameProperties.SCREEN_HEIGHT);
        content = getContentPane();

        instructLabel = new JLabel("FROGGER - Click 'Start' To Play!");
        instructLabel.setBounds(200, 0, 200, 50);
        content.add(instructLabel);

        frogger1.setX(275);
        frogger1.setY(400);
        frogger1.setWidth(161);
        frogger1.setHeight(200);
        frogger1.setImage("frogger.png");
        
        frogger2.setX(275);
        frogger2.setY(400);
        frogger2.setWidth(161);
        frogger2.setHeight(200);
        frogger2.setImage("frogger.png");

        froggerLabel = new JLabel();
        froggerImage = new ImageIcon(getClass().getResource("sprites/" + currentFrogger.getImage()));

        froggerLabel.setIcon(froggerImage);
        froggerLabel.setSize(currentFrogger.getWidth(), currentFrogger.getHeight());
        froggerLabel.setLocation(currentFrogger.getX(), currentFrogger.getY());
        currentFrogger.setFroggerLabel(froggerLabel);
        add(froggerLabel);

        carLogInit();

        startButton = new JButton("Start");
        startButton.setSize(80, 50);
        startButton.setLocation(gameProperties.SCREEN_WIDTH - 190, gameProperties.SCREEN_HEIGHT - 85);
        startButton.setFocusable(false);

        restartButton = new JButton("Restart");
        restartButton.setSize(80, 50);
        restartButton.setLocation(gameProperties.SCREEN_WIDTH - 100, gameProperties.SCREEN_HEIGHT - 85);
        restartButton.setFocusable(false);

        startButton.addActionListener(this);
        add(startButton);
        restartButton.addActionListener(this);
        add(restartButton);
        add(backgroundLabel);

        content.addKeyListener(this);
        content.setFocusable(true);

      //new thread
		
      		//send GET FROG\n to server every 500ms
      		//send GET LOGS\n
      		//send GET CARS\n
      		
      		//set up listening server to receive data sent 
      			//pass to froggerService
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException{
        SwingUtilities.invokeLater(() -> {
            gamePrep myGame = new gamePrep();
            myGame.setVisible(true);
        });
        
        final ServerSocket client = new ServerSocket(CLIENT_PORT);
        
      //set up listening server
      		Thread t1 = new Thread ( new Runnable () {
      			public void run ( ) {
      				synchronized(this) {
      					
      					System.out.println("Waiting for server responses...");
      					while(true) {
      						Socket s2;
      						try {
      							s2 = client.accept();
      							AService myService = new AService (s2);
      							Thread t = new Thread(myService);
      							t.start();
      							
      							
      						} catch (IOException e) {
      							// TODO Auto-generated catch block
      							e.printStackTrace();
      						}
      						System.out.println("client connected");	
      					}
      				}
      			}
      		});
      		t1.start( );
      		
      		//set up a communication socket
      		Socket s = new Socket("localhost", SERVER_PORT);
      		
      		//Initialize data stream to send data out
      		OutputStream outstream = s.getOutputStream();
      		PrintWriter out = new PrintWriter(outstream);

      		String command = "PLAYER 2 UP\n";
      		System.out.println("Sending: " + command);
      		out.println(command);
      		out.flush();
      		
      		command = "PLAYER 1 DOWN\n";
      		System.out.println("Sending: " + command);
      		out.println(command);
      		out.flush();
      		
      		s.close();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");

        if (!gameStarted) {
            System.out.println("Press the Start button to begin the game.");
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // SEND FROG UP\N
        	sendCommand("PLAYER " + currentPlayer + " UP\n");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // SEND FROG DOWN\N
        	sendCommand("PLAYER " + currentPlayer + " DOWN\n");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // SEND FROG LEFT\N
        	sendCommand("PLAYER " + currentPlayer + " LEFT\n");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // SEND FROG RIGHT\N
        	sendCommand("PLAYER " + currentPlayer + " RIGHT\n");
        }
    }
    
    private void sendCommand(String command) {
        try {
            Socket s = new Socket("localhost", SERVER_PORT);
            OutputStream outstream = s.getOutputStream();
            PrintWriter out = new PrintWriter(outstream);

            System.out.println("Sending: " + command);
            out.println(command);
            out.flush();

            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
        
        	
        }

        if (e.getSource() == restartButton) {
            // SEND RESTARTGAME\N command to server
            resetObjects();
            startCarsAndLogs();
            gameStarted = true;
        }
    }
}
