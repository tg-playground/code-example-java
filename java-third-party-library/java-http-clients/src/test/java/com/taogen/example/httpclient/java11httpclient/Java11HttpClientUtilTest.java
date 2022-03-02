package com.taogen.example.httpclient.java11httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
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
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Java11HttpClientUtilTest extends BaseTest {

    @Test
    void requestWithoutBody() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Java11HttpClientUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET
                , getBasicParam(), getBasicHeaders());
        System.out.println(response.body());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        assertTrue(response.body().contains("\"id\":1"));

        HttpResponse<String> response2 = Java11HttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + 1, HttpMethod.GET
                , getBasicParam(), getBasicHeaders());
        System.out.println(response2.body());
        assertEquals(200, response2.statusCode());
        assertNotNull(response2.body());
        assertTrue(response2.body().contains("\"id\":1"));
    }

    @Test
    void requestWithJson() throws IOException, URISyntaxException, InterruptedException {
        String name = "test" + System.currentTimeMillis();
        HttpResponse<String> response = Java11HttpClientUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.body());
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        assertTrue(response.body().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void requestWithFormUrlEncoded() throws URISyntaxException, IOException, InterruptedException {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        String name = "test" + System.currentTimeMillis();
        user.setName(name);
        HttpResponse<String> response = Java11HttpClientUtil.requestWithFormUrlEncoded(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.body());
        assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertNotNull(response.body());
        HttpResponse<String> getUserResponse = Java11HttpClientUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertTrue(getUserResponse.body().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void delete() throws IOException, URISyntaxException, InterruptedException {
        String name = "test" + System.currentTimeMillis();
        HttpResponse<String> response = Java11HttpClientUtil.requestWithJson(userEndpointUrl,
                HttpMethod.POST, getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.body());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.body(), User.class);
        Long id = user.getId();
        HttpResponse<String> deleteResponse = Java11HttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + id,
                HttpMethod.DELETE, getBasicParam(), getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.statusCode());
        HttpResponse<String> getResponse = Java11HttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertEquals("", getResponse.body());
    }

    @Test
    void requestWithFormData() throws IOException, URISyntaxException, InterruptedException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        body.add("token", UserController.RANDOM_TOKEN);
        HttpResponse<String> response = Java11HttpClientUtil.requestWithFormData(
                fileEndpointUrl + "/upload", HttpMethod.POST, null, getBasicHeaders(), body);
        System.out.println(response.body());
        assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertNotNull(response.body());
        assertTrue(response.body().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.body());
        HttpResponse<String> fileExistResponse = Java11HttpClientUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), fileExistResponse.statusCode());
        assertEquals("true", fileExistResponse.body());
        HttpResponse<String> deleteResponse = Java11HttpClientUtil.requestWithoutBody(fileEndpointUrl + "/deleteFile", HttpMethod.DELETE, fileExistQueryParam, getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.statusCode());
    }
}
