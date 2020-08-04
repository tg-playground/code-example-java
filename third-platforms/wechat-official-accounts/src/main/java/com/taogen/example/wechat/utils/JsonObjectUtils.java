package com.taogen.example.wechat.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Taogen
 */
public class JsonObjectUtils {
    public static final Logger logger = LogManager.getLogger();

    public static JSONObject getJsonObjectFromStr(String jsonStr) {
        JSONObject jsonObject = null;
        if (jsonStr != null) {
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                logger.error("{}: {}", e.getClass().getName(), e.getMessage());
            }
        }
        return jsonObject;
    }

    public static String getString(JSONObject jsonObject, String key) {
        String result = null;
        if (jsonObject != null) {
            try {
                result = jsonObject.getString(key);
            } catch (JSONException e) {
                logger.error("{}: {}", e.getClass().getName(), e.getMessage());
            }
        }
        return result;
    }

    public static Integer getInt(JSONObject jsonObject, String key) {
        Integer result = null;
        try {
            result = jsonObject.getInt(key);
        } catch (JSONException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
        return result;
    }

    public static JSONArray getJsonArray(JSONObject jsonObject, String key) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
        return jsonArray;
    }

}
