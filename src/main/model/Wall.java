package model;

import java.awt.Color;

// Represents a Wall the player cannot collide with
public class Wall {
    private int posX;
    private int posY;
    private int id;

    public static final Color COLOR = new Color(70, 70, 70);

    // EFFECTS: creates a wall at a location on the map
    public Wall(int x, int y, int id) {
        posX = x;
        posY = y;
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
    // EFFECTS: adds the given numbers to the position of the wall
    public void move(int x, int y) {
        posX += x;
        posY += y;
    }
}
