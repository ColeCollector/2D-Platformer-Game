package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWall {
    Wall wall;

    @BeforeEach
    void runBefore() {
        wall = new Wall(10, 20);
    }

    @Test
    void testConstructor() {
        assertEquals(wall.getX(), 10);
        assertEquals(wall.getY(), 20);
    }
}
