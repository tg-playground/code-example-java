package com.taogen.example.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class BaseTest {
    public static final String domain = "http://localhost";

    public static final String USER_ENDPOINT = "users";

    public static final String FILE_ENDPOINT = "files";

    @Value("${server.port}")
    protected String serverPort;

    protected String userEndpointUrl;

    protected String fileEndpointUrl;

    @PostConstruct
    public void init() {
        userEndpointUrl = new StringBuilder()
                .append(domain)
                .append(":")
                .append(serverPort)
                .append("/")
                .append(USER_ENDPOINT)
                .toString();
        fileEndpointUrl = new StringBuilder()
                .append(domain)
                .append(":")
                .append(serverPort)
                .append("/")
                .append(FILE_ENDPOINT)
                .toString();
    }

    protected static MultiValueMap<String, String> getBasicParam() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", UserController.RANDOM_TOKEN);
        params.add("chineseTest", UserController.CHINESE_TEST);
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

    protected MultiValueMap<String, String> getBasicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(UserController.APP_HEADER_KEY, UserController.APP_HEADER_VALUE);
        return headers;
    }
}
