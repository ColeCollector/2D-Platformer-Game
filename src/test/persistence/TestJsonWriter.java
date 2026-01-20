package persistence;

import model.Category;
import model.Entity;
import model.Map;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Credit to JsonSerializationDemo
class TestJsonWriter extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            Map map = new Map("Map 10");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMap() {
        try {
            Map map = new Map("Map 69");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
            map = reader.read();
            assertEquals("Map 69", map.getName());
            assertEquals(0, map.numEntities());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMap() {
        try {
            Map map = new Map("Map Infinity");
            map.addEntity(new Entity(Category.WALL, 50, 51, 0, 0));
            map.addEntity(new Entity(Category.ENEMY, 40, 50, 0, 0));
            map.addEntity(new Entity(Category.PLAYER, 30, 35, 2, 0));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            map = reader.read();
            assertEquals("Map Infinity", map.getName());
            List<Entity> entities = map.getEntities();
            assertEquals(3, entities.size());
            checkEntity(Category.WALL, 50, 51, 0, entities.get(0));
            checkEntity(Category.ENEMY, 40, 50, 0, entities.get(1));
            checkEntity(Category.PLAYER, 30, 35, 2, entities.get(2));
            map.removePlayer();
            assertEquals(2, entities.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}