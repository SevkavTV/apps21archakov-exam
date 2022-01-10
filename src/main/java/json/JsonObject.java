package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final Map<String, Json> jsons;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsons = new HashMap<>();
        for(JsonPair jsonPair: jsonPairs) {
            jsons.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjBody() + "}";
    }

    public void add(JsonPair jsonPair) {
        jsons.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsons.get(name);
    }

    public boolean contains(String name) {
        return find(name) != null;
    }

    public JsonObject projection(String... names) {
        JsonObject projectedJson = new JsonObject();

        for (String key: names) {
            if (this.find(key) != null) {
                JsonPair jsonPair = new JsonPair(key, this.find(key));
                projectedJson.add(jsonPair);
            }
        }

        return projectedJson;
    }

    private String getJsonObjBody(){
        StringBuilder jsonObjStr = new StringBuilder();
        Iterator<Map.Entry<String, Json>> jsonObjIterator = jsons.entrySet().iterator();
        while (jsonObjIterator.hasNext()) {
            Map.Entry<String, Json> jsonPair = jsonObjIterator.next();
            JsonString key = new JsonString(jsonPair.getKey());
            Json value = jsonPair.getValue();
            jsonObjStr.append(key.toJson() + ": " + value.toJson());
            if (jsonObjIterator.hasNext()) {
                jsonObjStr.append(", ");
            }
        }
        return jsonObjStr.toString();
    }
}
