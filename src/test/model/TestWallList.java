package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWallList {
    WallList wallList;

    @BeforeEach
    void runBefore() {
        wallList = new WallList();
    }

    @Test
    void testConstructor() {
        assertEquals(wallList.getList(), new ArrayList<>());
    }

    @Test
    void testAddWall() {
        List<Wall> testList = new ArrayList<>(); 
        Wall wall1 = new Wall(10, -20);
        Wall wall2 = new Wall(-64, 12);

        testList.add(wall1);
        wallList.addWall(wall1);
        assertEquals(wallList.getList(), testList);
        
        testList.add(wall2);
        wallList.addWall(wall2);
        assertEquals(wallList.getList(), testList);

        wallList.addWall(wall2);
        assertEquals(wallList.getList(), testList);
    }
    
    @Test
    void testhandleCollisions() {
        Player player = new Player(50, 50, 3);
        assertEquals(wallList.handleCollisions(player, -1, 0), false);
        wallList.addWall(new Wall(49, 50));
        assertEquals(wallList.handleCollisions(player, -1, 0), true);
        wallList.addWall(new Wall(50, 52));
        assertEquals(wallList.handleCollisions(player, 0, 1), false);
    }
}
