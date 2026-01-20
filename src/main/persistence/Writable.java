package persistence;

import org.json.JSONObject;

// Credit to JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
 