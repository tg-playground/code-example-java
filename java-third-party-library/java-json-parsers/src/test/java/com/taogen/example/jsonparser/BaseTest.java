package com.taogen.example.jsonparser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Taogen
 */
public class BaseTest {
    public static final String json = "{\"id\":1,\"name\":\"Jack\",\"age\":18,\"birthDate\":\"1990-01-01\", \"roles\": [{\"id\":1,\"name\":\"admin\"},{\"id\":2,\"name\":\"user\"}]}";
    public static final String jsonRoleArray = "[{\"id\":1,\"name\":\"admin\"},{\"id\":2,\"name\":\"user\"}]";

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

}
