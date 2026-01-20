package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;

public class TestEnemyList {
    EnemyList enemyList;
    Map map;

    @BeforeEach
    void runBefore() {
        enemyList = new EnemyList();
    }

    @Test
    void testConstructor() {
        assertEquals(enemyList.getList(), new ArrayList<>());
    }

    @Test
    void testAddEnemy() {
        List<Enemy> testList = new ArrayList<>(); 
        Enemy enemy1 = new Enemy(10, -20, 1);
        Enemy enemy2 = new Enemy(-64, 12, 2);

        testList.add(enemy1);
        enemyList.addEnemy(enemy1);
        assertEquals(enemyList.getList(), testList);
        
        testList.add(enemy2);
        enemyList.addEnemy(enemy2);
        assertEquals(enemyList.getList(), testList);

        enemyList.addEnemy(enemy2);
        assertEquals(enemyList.getList(), testList);
    }

    @Test
    void testRemoveEnemy() {
        List<Enemy> testList = new ArrayList<>(); 
        Enemy enemy1 = new Enemy(10, 10, 1);
        Enemy enemy2 = new Enemy(-64, 12, 2);

        enemyList.addEnemy(enemy1);
        enemyList.addEnemy(enemy2);
        testList.add(enemy2);

        enemyList.removeEnemy(enemy1);
        assertEquals(enemyList.getList(), testList);

        enemyList.removeEnemy(enemy1);
        assertEquals(enemyList.getList(), testList);
    }

    @Test
    void testhandleCollisions() {

        try {
            JsonReader reader = new JsonReader("./data/testEnemyGeneralMap.json");
            Map map = reader.read();
            Player player = new Player(50, 50, 3);
            assertEquals(enemyList.handleCollisions(player, -1, 0, map), false);
            enemyList.addEnemy(new Enemy(49, 50, 1));
            enemyList.addEnemy(new Enemy(50, 51, 2));
            assertEquals(enemyList.handleCollisions(player, 0, -1, map), false);
            assertEquals(enemyList.handleCollisions(player, 0, 1, map), false);
            assertEquals(enemyList.handleCollisions(player, -1, 0, map), true);
            player.move(false, -1, 1);
            assertEquals(enemyList.handleCollisions(player, 0, -1, map), true);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
