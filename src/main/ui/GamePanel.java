package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Player;
import model.Wall;
import model.Enemy;

/*
 * The panel in which the game is rendered.
 */

// Credit to B02-SpaceInvadersBase
public class GamePanel extends JPanel {
    public static final int SIZE = 45;
    private static final String OVER = "You Lost!";
    private static final String WIN = "You Won!";
    private static final String REPLAY = "R to replay";
    private Game game;

    // Constructs a game panel
    // effects:  sets size and background colour of panel, 
    //           updates this with the game to be displayed
    public GamePanel(Game g) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
    }
    
    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        drawGame(g);
        
        if (game.isOver()) {
            gameOver(g);
        }
    }
    
    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawEnemies(g);
        drawWalls(g);
    }
    
    // Draw the Player
    // modifies: g
    // effects:  draws the Player onto g
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        try {
            BufferedImage wallImage = ImageIO.read(getClass().getResource("player.png"));
            g.drawImage(wallImage, p.getX() * SIZE, p.getY() * SIZE, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // Draws the enemies
    // modifies: g
    // effects:  draws the enemies onto g
    private void drawEnemies(Graphics g) {
        for (Enemy next : game.getEnemies()) {
            drawEnemy(g, next);
        }
    }

    // Draw the Enemy
    // modifies: g
    // effects:  draws the Enemy onto g
    private void drawEnemy(Graphics g, Enemy e) {
        try {
            BufferedImage wallImage = ImageIO.read(getClass().getResource("enemy.png"));
            g.drawImage(wallImage, e.getX() * SIZE, e.getY() * SIZE, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Draw the Wall
    // modifies: g
    // effects:  draws the Wall onto g
    private void drawWall(Graphics g, Wall e) {
        try {
            BufferedImage wallImage = ImageIO.read(getClass().getResource("wall.png"));
            g.drawImage(wallImage, e.getX() * SIZE, e.getY() * SIZE, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // Draws the enemies
    // modifies: g
    // effects:  draws the enemies onto g
    private void drawWalls(Graphics g) {
        for (Wall next : game.getWalls()) {
            drawWall(g, next);
        }
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        if (game.getEnemies().size() == 0) {
            centreString(WIN, g, fm, Game.HEIGHT / 2);
        } else {
            centreString(OVER, g, fm, Game.HEIGHT / 2);
        }
        centreString(REPLAY, g, fm, Game.HEIGHT / 2 + 50);
        g.setColor(saved);
    }


    
    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int ypos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (Game.WIDTH - width) / 2, ypos);
    }
}
