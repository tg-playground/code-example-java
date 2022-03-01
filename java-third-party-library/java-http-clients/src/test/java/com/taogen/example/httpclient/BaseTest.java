package com.taogen.example.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class BaseTest {
    protected static MultiValueMap<String, String> getBasicParam() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", UserController.RANDOM_TOKEN);
        return params;
    }

    protected static MultiValueMap<String, String> convertObjectToMultiValueMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(object, Map.class);
        MultiValueMap<String, String> linkedMultiValueMap =
                CollectionUtils.toMultiValueMap(map.entrySet().stream()
                        .collect(Collectors.toMap(e -> e.getKey(),
                                e -> Arrays.asList(e.getValue().toString()))));
        return linkedMultiValueMap;
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        for (Field field : object.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            try {
//                params.add(field.getName(), field.get(object).toString());
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return params;
    }
}
