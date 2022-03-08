package com.taogen.example.httpclient.javahttpurlconnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import com.taogen.example.httpclient.javahttpurlconnection.vo.HttpUrlConnectionHttpResponse;
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
class JavaHttpUrlConnectionUtilTest extends BaseTest {

    @Test
    void requestWithoutBody() throws IOException {
        HttpUrlConnectionHttpResponse result = JavaHttpUrlConnectionUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result.getBody());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().contains("\"id\":1"));
        HttpUrlConnectionHttpResponse result2 = JavaHttpUrlConnectionUtil.requestWithoutBody(userEndpointUrl + "/1", HttpMethod.GET,
                getBasicParam(), getBasicHeaders());
        System.out.println(result2.getBody());
        assertEquals(HttpStatus.OK.value(), result2.getStatusCode());
        assertNotNull(result2.getBody());
        assertTrue(result2.getBody().contains("\"id\":1"));
    }

    @Test
    void delete() throws IOException {
        String name = "test" + System.currentTimeMillis();
        HttpUrlConnectionHttpResponse response = JavaHttpUrlConnectionUtil.requestWithJson(userEndpointUrl,
                HttpMethod.POST, getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getBody(), User.class);
        Long id = user.getId();
        HttpUrlConnectionHttpResponse deleteResponse = JavaHttpUrlConnectionUtil.requestWithoutBody(userEndpointUrl + "/" + id,
                HttpMethod.DELETE, getBasicParam(), getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
        HttpUrlConnectionHttpResponse getResponse = JavaHttpUrlConnectionUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertEquals("", getResponse.getBody());
    }

    @Test
    void requestWithJson() throws IOException {
        String name = "test" + System.currentTimeMillis();
        HttpUrlConnectionHttpResponse response = JavaHttpUrlConnectionUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void requestWithForm() throws IOException {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        String name = "test" + System.currentTimeMillis();
        user.setName(name);
        HttpUrlConnectionHttpResponse response = JavaHttpUrlConnectionUtil.requestWithForm(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        HttpUrlConnectionHttpResponse getUserResponse = JavaHttpUrlConnectionUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertTrue(getUserResponse.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void requestWithMultipart() throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        HttpUrlConnectionHttpResponse response = JavaHttpUrlConnectionUtil.requestWithMultipart(fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), getBasicHeaders(), body);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.getBody());
        HttpUrlConnectionHttpResponse fileExistResponse = JavaHttpUrlConnectionUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getBasicHeaders());
        assertEquals("true", fileExistResponse.getBody());
        HttpUrlConnectionHttpResponse deleteResponse = JavaHttpUrlConnectionUtil.requestWithoutBody(fileEndpointUrl + "/deleteFile", HttpMethod.DELETE, fileExistQueryParam, getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
    }
}
