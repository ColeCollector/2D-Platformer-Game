package model;

import java.util.List;

import java.util.ArrayList;

// Represents a list of Walls
public class WallList {
    private List<Wall> wallList;
    private EnemyList enemyList;
    private Player player;
    private Map map;
    
    // EFFECTS: creates an empty list of walls
    public WallList(Map map, EnemyList enemyList) {
        wallList = new ArrayList<>();
        this.map = map;
        this.enemyList = enemyList;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // MODIFIES: this
    public void addWall(Wall wall) {
        if (!wallList.contains(wall)) {
            wallList.add(wall);
        }
    }

    public List<Wall> getList() {
        return wallList;
    }

    // MODIFIES: this
    // EFFECTS: if the player is at the same point as a wall in the list, return true
    public boolean handleCollisions(int x, int y) {
        for (Wall wall : wallList) {
            if (wall.getX() == player.getX() + x && wall.getY() == player.getY() + y) {
                for (Wall wall2 : wallList) {
                    if (wall.getX() + x == wall2.getX() && wall.getY() + y == wall2.getY()) {
                        return true;
                    }
                }

                for (Enemy enemy : enemyList.getList()) {
                    if (wall.getX() + x == enemy.getX() && wall.getY() + y == enemy.getY()) {
                        return true;
                    }
                }

                wall.move(x, y);
                map.removeEntity(wall.getid());
                map.addEntity(new Entity(Category.WALL, wall.getX(), wall.getY(), 0, wall.getid()));
                return false;
            }
        }
        return false;
    }
}
