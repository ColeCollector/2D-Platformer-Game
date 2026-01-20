package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an entity having a name and a category
public class Entity implements Writable {
    private Category category;
    private int posX;
    private int posY;
    private int health;
    private int id;

    // EFFECTS: constructs an entity with a category and position
    public Entity(Category category, int x, int y, int h, int id) {
        this.category = category;
        this.posX = x;
        this.posY = y;
        this.health = h;
        this.id = id;
    }

    public int getX() {
        return posX;
    }
    
    public int getY() {
        return posY;
    }

    public int getid() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("x", posX);
        json.put("y", posY);

        if (category == Category.PLAYER) {
            json.put("health", health);
            
        } else {
            json.put("id", id);
        }

        return json;
    }
}
