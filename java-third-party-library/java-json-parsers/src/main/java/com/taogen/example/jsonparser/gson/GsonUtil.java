package com.taogen.example.jsonparser.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.taogen.example.jsonparser.gson.jsondeserializer.MapDeserializerDoubleAsIntFix;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Gson Util
 * <p>
 * 1. Convert json string to object(entity, map, jsonObject, List<entity>) and vice versa.
 * 2. Conversion between entity, map and jsonObject
 *
 * @author Taogen
 */
public class GsonUtil {
    public static final JsonSerializer<Date> DATE_JSON_SERIALIZER = (src, typeOfSrc, context) -> src == null ? null
            : new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd").format(src));

    private GsonUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String objectToJsonStr(Object obj) {
        return new Gson().toJson(obj);
    }

    public static String objectToJsonStr(Object obj, Map<Type, Object> typeAdapters) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        registerTypeAdapters(gsonBuilder, typeAdapters);
        return gsonBuilder.create().toJson(obj);
    }

    public static String mapToJsonStr(Map map) {
        return new Gson().toJson(map);
    }

    public static String mapToJsonStr(Map<String, Object> map, Map<Type, Object> typeAdapters) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        registerTypeAdapters(gsonBuilder, typeAdapters);
        return gsonBuilder.create().toJson(map);
    }

    private static void registerTypeAdapters(GsonBuilder gsonBuilder, Map<Type, Object> typeAdapters) {
        if (typeAdapters != null) {
            for (Map.Entry<Type, Object> entry : typeAdapters.entrySet()) {
                gsonBuilder.registerTypeAdapter(entry.getKey(), entry.getValue());
            }
        }
    }

    public static String jsonObjToJsonStr(JsonObject jsonObject) {
        return new Gson().toJson(jsonObject);
    }

    public static String listToJsonStr(List list) {
        return new Gson().toJson(list);
    }

    public static <T> T jsonStrToObject(String jsonStr, Class<T> clazz) {
        return new Gson().fromJson(jsonStr, clazz);
    }

    public static Map<String, Object> jsonStrToMap(String jsonStr) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new MapDeserializerDoubleAsIntFix());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
        }.getType());
        // error result (int become float): {id=1.0, name=Jack, age=18.0, birthDate=1990-01-01, roles=[{id=1.0, name=admin}, {id=2.0, name=user}]}
        // return new Gson().fromJson(jsonStr, Map.class);
    }

    public static JsonObject jsonStrToJsonObj(String jsonStr) {
        return new Gson().fromJson(jsonStr, JsonObject.class);
    }

    public static <T> List<T> jsonArrayStrToList(String jsonStr, Class<T[]> clazz) {
        return new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonStr, clazz)));
//        return new Gson().fromJson(jsonStr, new TypeToken<List<T>>(){}.getType());
    }
}