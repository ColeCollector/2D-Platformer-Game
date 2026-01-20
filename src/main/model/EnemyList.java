package model;

import java.util.List;

import java.util.ArrayList;

// Represents a list of Enemies
public class EnemyList {
    private List<Enemy> enemyList;
    private Player player;
    private Map map;

    // EFFECTS: creates an empty list of enemies
    public EnemyList(Map map) {
        enemyList = new ArrayList<>();
        this.map = map;
        EventLog.getInstance().logEvent(new Event("EnemyList reset"));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Enemy> getList() {
        return enemyList;
    }

    // MODIFIES: this
    public void addEnemy(Enemy enemy) {
        if (!enemyList.contains(enemy)) {
            enemyList.add(enemy);
            EventLog.getInstance().logEvent(new Event("Enemy added to EnemyList"));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the first enemy of the list
    public void removeEnemy(Enemy enemy) {
        if (enemyList.contains(enemy)) {
            enemyList.remove(enemy);
            EventLog.getInstance().logEvent(new Event("Enemy removed from EnemyList"));
        }
    }

    // MODIFIES: this
    // EFFECTS: if the player and one of the enemies are on the same point after the player moves by x and y, if
    //          the player is coming from above remove the enemy and return false, otherwise return true. 
    //          if none of these are the case then return false
    public void handleCollisions(int x, int y) {
        for (Enemy enemy : enemyList) {
            if (enemy.getX() == player.getX() + x && enemy.getY() == player.getY() + y) {
                removeEnemy(enemy);
                map.removeEntity(enemy.getid());
                break;
            }
        }
    }
}