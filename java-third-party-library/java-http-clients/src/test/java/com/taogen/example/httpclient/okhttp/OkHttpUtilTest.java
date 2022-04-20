package com.taogen.example.httpclient.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import com.taogen.example.httpclient.okhttp.vo.OkHttpResponse;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OkHttpUtilTest extends BaseTest {

    @Test
    void sendGet() throws IOException {
        OkHttpResponse result = OkHttpUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET,
                getBasicParam(), getOkHttpBasicHeaders());
        System.out.println(result.getBody());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBodyString().contains("\"id\":1"));
        OkHttpResponse result2 = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/1", HttpMethod.GET,
                getBasicParam(), getOkHttpBasicHeaders());
        System.out.println(result2.getBody());
        assertEquals(HttpStatus.OK.value(), result2.getStatusCode());
        assertNotNull(result2.getBody());
        assertTrue(result2.getBodyString().contains("\"id\":1"));
    }

    @Test
    void postWithJson() throws IOException {
        String name = "test" + System.currentTimeMillis();
        OkHttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getOkHttpBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBodyString().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void putWithFormUrlEncoded() throws IOException {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        String name = "test" + System.currentTimeMillis();
        user.setName(name);
        OkHttpResponse response = OkHttpUtil.requestWithFormUrlEncoded(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getOkHttpBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        OkHttpResponse getUserResponse = OkHttpUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getOkHttpBasicHeaders());
        assertTrue(getUserResponse.getBodyString().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void delete() throws IOException {
        String name = "test" + System.currentTimeMillis();
        OkHttpResponse response = OkHttpUtil.requestWithJson(userEndpointUrl,
                HttpMethod.POST, getBasicParam(), getOkHttpBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getBody(), User.class);
        Long id = user.getId();
        OkHttpResponse deleteResponse = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id,
                HttpMethod.DELETE, getBasicParam(), getOkHttpBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
        OkHttpResponse getResponse = OkHttpUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getOkHttpBasicHeaders());
        assertEquals("", getResponse.getBodyString());
    }

    @Test
    void sendWithFormData() throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        OkHttpResponse response = OkHttpUtil.requestWithFormData(fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), getOkHttpBasicHeaders(), body);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBodyString().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.getBodyString());
        OkHttpResponse fileExistResponse = OkHttpUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getOkHttpBasicHeaders());
        assertEquals("true", fileExistResponse.getBodyString());
        OkHttpResponse deleteResponse = OkHttpUtil.requestWithoutBody(fileEndpointUrl + "/deleteFile", HttpMethod.DELETE, fileExistQueryParam, getOkHttpBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
    }

    protected Headers getOkHttpBasicHeaders() {
        Headers headers = new Headers.Builder()
                .add(UserController.APP_HEADER_KEY, UserController.APP_HEADER_VALUE)
                .build();
        return headers;
    }
}
