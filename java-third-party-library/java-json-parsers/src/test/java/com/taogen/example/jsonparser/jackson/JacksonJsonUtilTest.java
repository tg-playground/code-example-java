package com.taogen.example.jsonparser.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.taogen.example.jsonparser.jackson.entity.Role;
import com.taogen.example.jsonparser.jackson.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class JacksonJsonUtilTest {

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final String json = "{\"id\":1,\"name\":\"Jack\",\"age\":18,\"birthDate\":\"1990-01-01\", \"roles\": [{\"id\":1,\"name\":\"admin\"},{\"id\":2,\"name\":\"user\"}]}";
    public static final String jsonRoleArray = "[{\"id\":1,\"name\":\"admin\"},{\"id\":2,\"name\":\"user\"}]";

    public static User user;

    static {
        try {
            user = new User().builder()
                    .id(1L)
                    .name("Jack")
                    .age(18)
                    .birthDate(dateFormat.parse("1990-01-01"))
                    .roles(Arrays.asList(new Role(1L, "admin"), new Role(2L, "user")))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    void objectToMap() {
        Map<String, Object> map = JacksonJsonUtil.objectToMap(user);
        log.debug("map: {}", map);
        assertNotNull(map);
        assertEquals(1L, Long.valueOf(map.get("id").toString()));
    }

    @Test
    void mapToObject() {
        Map<String, Object> map = JacksonJsonUtil.objectToMap(user);
        User user = JacksonJsonUtil.mapToObject(map, User.class);
        log.debug(user);
        assertNotNull(user);
        assertEquals(1L, user.getId().longValue());
    }

    @Test
    void objectToJsonStr() throws JsonProcessingException {
        String jsonStr = JacksonJsonUtil.objectToJsonStr(user);
        log.debug(jsonStr);
        assertNotNull(jsonStr);
        assertTrue(jsonStr.contains("\"id\":1"));
        assertTrue(jsonStr.contains("roles"));
    }

    @Test
    void mapToJsonStr() throws JsonProcessingException {
        Map<String, Object> map = JacksonJsonUtil.objectToMap(user);
        String jsonStr = JacksonJsonUtil.mapToJsonStr(map);
        log.debug(jsonStr);
        assertNotNull(jsonStr);
        assertTrue(jsonStr.contains("\"id\":1"));
        assertTrue(jsonStr.contains("roles"));
    }

    @Test
    void jsonStrToObject() throws JsonProcessingException {
        User user = JacksonJsonUtil.jsonStrToObject(json, User.class);
        log.debug(user);
        assertNotNull(user);
        assertEquals(1L, user.getId().longValue());
    }

    @Test
    void jsonStrToMap() throws JsonProcessingException {
        Map<String, Object> map = JacksonJsonUtil.jsonStrToMap(json);
        log.debug(map);
        assertNotNull(map);
        assertEquals(1L, Long.valueOf(map.get("id").toString()));
    }

    @Test
    void jsonStrToJsonObject() throws JsonProcessingException {
        JsonNode jsonNode = JacksonJsonUtil.jsonStrToJsonObject(json);
        log.debug(jsonNode);
        assertNotNull(jsonNode);
        assertEquals(1L, jsonNode.get("id").asLong());
        JsonNode roles = jsonNode.get("roles");
        roles.elements().forEachRemaining(log::debug);
        assertEquals(2, roles.size());
    }

    @Test
    void jsonArrayStrToList() throws JsonProcessingException {
        List<Role> roles = JacksonJsonUtil.jsonArrayStrToList(jsonRoleArray, Role.class);
        log.debug(roles);
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }
}
