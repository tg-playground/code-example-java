package com.taogen.example.httpclient.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import com.taogen.example.httpclient.okhttp.vo.HttpResponse;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OkHttpUtilTest extends BaseTest {
    public static final String domain = "http://localhost";

    public static final String USER_ENDPOINT = "users";

    public static final String FILE_ENDPOINT = "files";

    @Value("${server.port}")
    private String serverPort;

    private String userEndpointUrl;

    private String fileEndpointUrl;

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

    @Test
    void sendGet() throws IOException {
        HttpResponse result = OkHttpUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result.getBody());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains("\"id\":1"));
        HttpResponse result2 = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/1", HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result2.getBody());
        assertNotNull(result2.getBody());
        assertTrue(result2.getBody().contains("\"id\":1"));
    }

    @Test
    void postWithJson() throws IOException {
        String name = "test" + System.currentTimeMillis();
        HttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void putWithFormUrlEncoded() throws IOException {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        String name = "test" + System.currentTimeMillis();
        user.setName(name);
        HttpResponse response = OkHttpUtil.requestWithFormUrlEncoded(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
        HttpResponse getUserResponse = OkHttpUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertTrue(getUserResponse.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void delete() throws IOException {
        String name = "test" + System.currentTimeMillis();
        HttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getBody(), User.class);
        Long id = user.getId();
        OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.DELETE,
                getBasicParam(), getBasicHeaders());
        HttpResponse getResponse = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertEquals("", getResponse.getBody());
    }

    @Test
    void sendWithFormData() throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        HttpResponse response = OkHttpUtil.requestWithFormData(fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), getBasicHeaders(), body);
        System.out.println(response.getBody());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.getBody());
        HttpResponse fileExistResponse = OkHttpUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getBasicHeaders());
        assertEquals("true", fileExistResponse.getBody());
        OkHttpUtil.requestWithoutBody(fileEndpointUrl + "/deleteFile1", HttpMethod.DELETE, fileExistQueryParam, getBasicHeaders());
    }

    private Headers getBasicHeaders() {
        Headers headers = new Headers.Builder()
                .add(UserController.APP_HEADER_KEY, UserController.APP_HEADER_VALUE)
                .build();
        return headers;
    }
}
