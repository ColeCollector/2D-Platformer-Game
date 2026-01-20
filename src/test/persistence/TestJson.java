package persistence;

import model.Category;
import model.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Credit to JsonSerializationDemo
public class TestJson {
    protected void checkEntity(Category category, int x, int y, int health, Entity entity) {
        assertEquals(x, entity.getX());
        assertEquals(y, entity.getY());
        assertEquals(health, entity.getHealth());
        assertEquals(category, entity.getCategory());
    }
}
