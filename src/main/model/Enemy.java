package model;

import java.awt.Color;

// Represents an Enemy that can damage the player
public class Enemy {
    private int posX;
    private int posY;
    private int startX;
    private int startY;
    private int id;

    public static final Color COLOR = new Color(255, 0, 0);

    // EFFECTS: creates an enemy at a location on the map
    public Enemy(int x, int y, int id) {
        posX = x;
        posY = y;
        startX = x;
        startY = y;
        this.id = id;
    }

    public int getX() {
        return posX;
    }
    
    public int getY() {
        return posY;
    }

    public int getid() {
        return id;
    }

    // MODIFIES: this
    // EFFECTS: resets the enemies position
    public void reset() {
        posX = startX;
        posY = startY;
    }

    // MODIFIES: this
    // EFFECTS: adds the given numbers to the position of the enemy
    public void move(int x, int y) {
        posX += x;
        posY += y;
    }
}
