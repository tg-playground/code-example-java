package com.taogen.example.jsonparser.orgjson;

import com.taogen.example.jsonparser.BaseTest;
import com.taogen.example.jsonparser.jackson.entity.Role;
import com.taogen.example.jsonparser.jackson.entity.User;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class OrgJsonUtilTest extends BaseTest {

    public static User user;

    public static List<Role> roles;

    static {
        roles = Arrays.asList(new Role(1L, "admin"), new Role(2L, "user"));
        try {
            user = new User().builder()
                    .id(1L)
                    .name("Jack")
                    .age(18)
                    .birthDate(dateFormat.parse("1990-01-01"))
                    .roles(roles)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void jsonStrToJsonObject() {
        JSONObject jsonObject = OrgJsonUtil.jsonStrToJsonObject(json);
        log.debug(jsonObject);
        validateJsonObject(jsonObject);
    }

    private void validateJsonObject(JSONObject jsonObject) {
        assertNotNull(jsonObject);
        assertEquals(1L, jsonObject.getLong("id"));
        assertEquals("Jack", jsonObject.getString("name"));
        assertEquals(18, jsonObject.getInt("age"));
        assertEquals("1990-01-01", jsonObject.getString("birth_date"));
    }

    @Test
    void entityToJsonObject() {
        JSONObject jsonObject = OrgJsonUtil.entityToJsonObject(user);
        log.info(jsonObject);
        assertNotNull(jsonObject);
        assertEquals(1L, jsonObject.getLong("id"));
    }

    @Test
    void mapToJsonObject() {
        JSONObject jsonObject = OrgJsonUtil.mapToJsonObject(USER_MAP);
        log.debug(jsonObject);
        assertNotNull(jsonObject);
        assertEquals(1L, jsonObject.getLong("id"));
    }

    @Test
    void jsonObjectToJsonStr() {
        String jsonStr = OrgJsonUtil.jsonObjectToJsonStr(OrgJsonUtil.jsonStrToJsonObject(json));
        log.debug(jsonStr);
        assertNotNull(jsonStr);
        assertTrue(jsonStr.contains("\"id\":1,"));
    }

    @Test
    void jsonStrToJsonArray() {
        JSONArray jsonArray = OrgJsonUtil.jsonStrToJsonArray(jsonRoleArray);
        log.debug(jsonArray);
        validateJsonArray(jsonArray);
    }

    private void validateJsonArray(JSONArray jsonArray) {
        assertNotNull(jsonArray);
        assertEquals(2, jsonArray.length());
    }

    @Test
    void entityToJsonArray() {
        JSONArray jsonArray = OrgJsonUtil.entityToJsonArray(roles);
        log.debug(jsonArray);
        validateJsonArray(jsonArray);
    }

    @Test
    void jsonArrayToJsonStr() {
        String jsonStr = OrgJsonUtil.jsonArrayToJsonStr(OrgJsonUtil.jsonStrToJsonArray(jsonRoleArray));
        log.debug(jsonStr);
        assertNotNull(jsonStr);
        assertTrue(jsonStr.contains("\"id\":1"));
    }
}
