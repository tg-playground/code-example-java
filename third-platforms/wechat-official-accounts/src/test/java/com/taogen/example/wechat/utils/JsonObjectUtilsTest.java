package com.taogen.example.wechat.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonObjectUtilsTest {

    @Test
    public void getJsonObjectFromJsonStr() throws JSONException {
        // source string is null
        assertNull(JsonObjectUtils.getJsonObjectFromStr(null));
        // error format
        assertNull(JsonObjectUtils.getJsonObjectFromStr(""));
        assertNull(JsonObjectUtils.getJsonObjectFromStr("{"));
        // right format
        JSONObject jsonObject = JsonObjectUtils.getJsonObjectFromStr("{name:123}");
        assertNotNull(jsonObject);
        assertEquals("123", jsonObject.getString("name"));
        JSONObject jsonObject2 = JsonObjectUtils.getJsonObjectFromStr("{\"name\":\"123\"}");
        assertNotNull(jsonObject2);
        assertNotNull("123", jsonObject2.getString("name"));

    }

    @Test
    public void getStringFromJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject("{\"name\": \"123\"}");
        assertEquals("123", JsonObjectUtils.getString(jsonObject, "name"));
        assertNull(JsonObjectUtils.getString(jsonObject, "name1"));
        assertNull(JsonObjectUtils.getString(jsonObject, null));
    }
}