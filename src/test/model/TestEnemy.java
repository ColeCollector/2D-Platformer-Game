package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEnemy {
    Enemy enemy;

    @BeforeEach
    void runBefore() {
        enemy = new Enemy(10, 20, 1);
    }

    @Test
    void testConstructor() {
        assertEquals(enemy.getX(), 10);
        assertEquals(enemy.getY(), 20);
    }

    @Test
    void testMove() {
        enemy.move(3, 0);
        assertEquals(enemy.getX(), 13);
        assertEquals(enemy.getY(), 20);

        enemy.move(2, 1);
        assertEquals(enemy.getX(), 15);
        assertEquals(enemy.getY(), 21);
        
        enemy.move(0, -2);
        assertEquals(enemy.getX(), 15);
        assertEquals(enemy.getY(), 19);

        enemy.move(-1, 0);
        assertEquals(enemy.getX(), 14);
        assertEquals(enemy.getY(), 19);
    }

    @Test
    void testReset() {
        enemy.move(-10, 4);
        enemy.reset();
        assertEquals(enemy.getX(), 10);
        assertEquals(enemy.getY(), 20);
    }
}
