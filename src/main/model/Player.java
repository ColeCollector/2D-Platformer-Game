package model;

import java.awt.Color;

// Represents a Player
public class Player {
    private int posX;
    private int posY;
    private int startM;
    private int startX;
    private int startY;
    private int moves;

    public static final Color COLOR = new Color(0, 255, 0);

    // EFFECTS: creates a player at a location on the map
    // REQUIRES: h > 0 
    public Player(int x, int y, int h) {
        moves = h;
        posX = x;
        posY = y;
        startM = h;
        startX = x;
        startY = y;
    }

    public int getX() {
        return posX;
    }
    
    public int getY() {
        return posY;
    }

    public int getMoves() {
        return moves;
    }
    
    // MODIFIES: this
    // EFFECTS: adds the given numbers to the position of the player if there isn't a wall there
    public void move(boolean bool, int x, int y) {
        if (!bool) {
            posX += x;
            posY += y;
            moves -= 1;
        }
    }
    
    
    // MODIFIES: this
    // EFFECTS: resets the players position and moves
    public void reset() {
        posX = startX;
        posY = startY;
        moves = startM;
    }
}
