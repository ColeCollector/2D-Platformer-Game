package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

/*
 * Represents the main window in which the platformer
 * game is played
 */
public class LevelApp extends JFrame {

    private static final int INTERVAL = 10;
    private Game game;
    private GamePanel gp;
    private ScorePanel sp;

    // Constructs main window
    // effects: sets up window in which Puzzle game will be played
    public LevelApp() {
        super("Puzzle Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(false);

        addWindowListener(new WindowAdapter() {
            @Override 
            public void windowClosing(WindowEvent e) {
                game.printEvents();
                dispose();
                System.exit(0);
            }
        });

        game = new Game();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        
        add(gp);
        add(sp, BorderLayout.NORTH);

        sp.setFocusable(true);
        sp.requestFocusInWindow();
        sp.addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gp.repaint(); 
                sp.requestFocusInWindow();
                sp.update();
            }
        });
        
        t.start();
    }
    
    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
    
    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.playerControl(e.getKeyCode());
        }
    }
    
    /*
     * Play the game
     */
    public static void main(String[] args) {
        new LevelApp();
    }
}
