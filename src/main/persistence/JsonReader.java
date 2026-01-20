package persistence;

import model.Category;
import model.Entity;
import model.Map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Credit to JsonSerializationDemo
// Represents a reader that reads map from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads map from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Map read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses map from JSON object and returns it
    private Map parseMap(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Map map = new Map(name);
        addEntities(map, jsonObject);
        return map;
    }

    // MODIFIES: map
    // EFFECTS: parses entities from JSON object and adds them to map
    private void addEntities(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entities");
        for (Object json : jsonArray) {
            JSONObject nextObject = (JSONObject) json;
            addObject(map, nextObject);
        }
    }

    // MODIFIES: map
    // EFFECTS: parses entity from JSON object and adds it to map
    private void addObject(Map map, JSONObject jsonObject) {
        Category category = Category.valueOf(jsonObject.getString("category"));
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");

        // Default values though we aren't using them:
        int h = 0;
        int id = 0;

        if (category == Category.PLAYER) {
            h = jsonObject.getInt("health");
        } else {
            id = jsonObject.getInt("id");
        }

        Entity entity = new Entity(category, x, y, h, id);
        map.addEntity(entity);
    }
}
