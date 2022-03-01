package com.taogen.example.httpclient.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import com.taogen.example.httpclient.okhttp.vo.OkHttpResponse;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
        OkHttpResponse result = OkHttpUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result.getBody());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains("\"id\":1"));
        OkHttpResponse result2 = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/1", HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result2.getBody());
        assertEquals(HttpStatus.OK.value(), result2.getStatusCode());
        assertNotNull(result2.getBody());
        assertTrue(result2.getBody().contains("\"id\":1"));
    }

    @Test
    void postWithJson() throws IOException {
        String name = "test" + System.currentTimeMillis();
        OkHttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
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
        OkHttpResponse response = OkHttpUtil.requestWithFormUrlEncoded(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        OkHttpResponse getUserResponse = OkHttpUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertTrue(getUserResponse.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void delete() throws IOException {
        String name = "test" + System.currentTimeMillis();
        OkHttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl,
                HttpMethod.POST, getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getBody(), User.class);
        Long id = user.getId();
        OkHttpResponse deleteResponse = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id,
                HttpMethod.DELETE, getBasicParam(), getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
        OkHttpResponse getResponse = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertEquals("", getResponse.getBody());
    }

    @Test
    void sendWithFormData() throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        OkHttpResponse response = OkHttpUtil.requestWithFormData(fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), getBasicHeaders(), body);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.getBody());
        OkHttpResponse fileExistResponse = OkHttpUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getBasicHeaders());
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
