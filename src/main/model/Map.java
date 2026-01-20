package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

// Credit to JsonSerializationDemo
// Represents a map having a collection of entities
public class Map implements Writable {
    private String name;
    private List<Entity> entities;

    // EFFECTS: constructs map with a name and empty list of entities
    public Map(String name) {
        this.name = name;
        entities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds entity to this map
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // MODIFIES: this
    // EFFECTS: removes entities with id from the map
    public void removeEntity(int id) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity.getid() == id) {
                iterator.remove();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes players from the map
    public void removePlayer() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity enemy = iterator.next();
            if (enemy.getCategory() == Category.PLAYER) {
                iterator.remove();
            }
        }
    }

    // EFFECTS: returns an unmodifiable list of entities in this map
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    // EFFECTS: returns number of entities in this map
    public int numEntities() {
        return entities.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("entities", entitiesToJson());
        return json;
    }

    // EFFECTS: returns entities in this map as a JSON array
    private JSONArray entitiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entity e : entities) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}

