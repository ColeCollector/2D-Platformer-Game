package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    Player player;

    @BeforeEach
    void runBefore() {
        player = new Player(100, 100, 3);
    }

    @Test
    void testConstructor() {
        assertEquals(player.getX(), 100);
        assertEquals(player.getY(), 100);
        assertEquals(player.getHealth(), 3);
    }

    @Test
    void testMove() {
        player.move(true, 1, 1);
        assertEquals(player.getX(), 100);
        assertEquals(player.getY(), 100);

        player.move(false, 1, 2);
        assertEquals(player.getX(), 101);
        assertEquals(player.getY(), 102);

        player.move(true, -1, 0);
        assertEquals(player.getX(), 101);
        assertEquals(player.getY(), 102);

        player.move(false, -2, 0);
        assertEquals(player.getX(), 99);
        assertEquals(player.getY(), 102);
    }

    @Test
    void testHealth() {
        player.takeDamage();
        assertEquals(player.getHealth(), 2);
        player.takeDamage();
        player.takeDamage();
        assertEquals(player.getHealth(), 0);
    }

    @Test
    void testReset() {
        player.move(true,-10, 4);
        player.takeDamage();
        player.takeDamage();
        player.takeDamage();
        player.reset();
        assertEquals(player.getX(), 100);
        assertEquals(player.getY(), 100);
        assertEquals(player.getHealth(), 3);
        
        
    }
}
