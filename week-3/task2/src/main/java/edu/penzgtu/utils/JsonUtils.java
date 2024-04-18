package edu.penzgtu.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtils {

    private static final Gson gson = new Gson();
    private static final JsonParser jsonParser = new JsonParser();

    public static <T> T parseJsonToObject(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    public static JsonElement parseStringToJsonElement(String jsonString) {
        return jsonParser.parse(jsonString);
    }
}
