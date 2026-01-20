package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Map;
import model.Category;
import model.Entity;

// Credit to JsonSerializationDemo
public class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Map map = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMap() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMap.json");
        try {
            Map map = reader.read();
            assertEquals("Empty Map", map.getName());
            assertEquals(0, map.numEntities());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMap() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMap.json");
        try {
            Map map = reader.read();
            assertEquals("General Map", map.getName());
            List<Entity> entities = map.getEntities();
            assertEquals(3, entities.size());
            checkEntity(Category.PLAYER, 50, 50, 3, entities.get(0));
            checkEntity(Category.ENEMY, 45, 40, 0, entities.get(1));
            checkEntity(Category.WALL, 52, 50, 0, entities.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
}
