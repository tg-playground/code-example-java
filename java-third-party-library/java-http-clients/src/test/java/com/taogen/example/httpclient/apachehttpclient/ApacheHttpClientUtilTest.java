package com.taogen.example.httpclient.apachehttpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import com.taogen.example.httpclient.apachehttpclient.vo.ApacheHttpResponse;
import org.apache.hc.core5.http.ParseException;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApacheHttpClientUtilTest extends BaseTest {

    @Test
    void requestWithoutBody() throws IOException, URISyntaxException, ParseException {
        ApacheHttpResponse response = ApacheHttpClientUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET
                , getBasicParam(), getBasicHeaders());
        System.out.println(response.getBody());
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"id\":1"));

        ApacheHttpResponse response2 = ApacheHttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + 1, HttpMethod.GET
                , getBasicParam(), getBasicHeaders());
        System.out.println(response2.getBody());
        assertEquals(200, response2.getStatusCode());
        assertNotNull(response2.getBody());
        assertTrue(response2.getBody().contains("\"id\":1"));
    }

    @Test
    void delete() throws IOException, URISyntaxException, ParseException {
        String name = "test" + System.currentTimeMillis();
        ApacheHttpResponse response = ApacheHttpClientUtil.requestWithJson(userEndpointUrl,
                HttpMethod.POST, getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getBody(), User.class);
        Long id = user.getId();
        ApacheHttpResponse deleteResponse = ApacheHttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + id,
                HttpMethod.DELETE, getBasicParam(), getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
        ApacheHttpResponse getResponse = ApacheHttpClientUtil.requestWithoutBody(userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertEquals("", getResponse.getBody());
    }

    @Test
    void requestWithJson() throws IOException, URISyntaxException, ParseException {
        String name = "test" + System.currentTimeMillis();
        ApacheHttpResponse response = ApacheHttpClientUtil.requestWithJson(userEndpointUrl, HttpMethod.POST,
                getBasicParam(), getBasicHeaders(), "{\"name\":\"" + name + "\"}");
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void requestWithForm() throws IOException, URISyntaxException, ParseException {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        String name = "test" + System.currentTimeMillis();
        user.setName(name);
        ApacheHttpResponse response = ApacheHttpClientUtil.requestWithForm(userEndpointUrl + "/" + id, HttpMethod.PUT,
                getBasicParam(), getBasicHeaders(), convertObjectToMultiValueMap(user));
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        ApacheHttpResponse getUserResponse = ApacheHttpClientUtil.requestWithoutBody(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders());
        assertTrue(getUserResponse.getBody().contains("\"name\":\"" + name + "\""));
    }

    @Test
    void requestWithMultipart() throws IOException, URISyntaxException, ParseException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", file);
        ApacheHttpResponse response = ApacheHttpClientUtil.requestWithMultipart(fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), getBasicHeaders(), body);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains(file.getName()));
        MultiValueMap<String, String> fileExistQueryParam = getBasicParam();
        fileExistQueryParam.add("fileUri", response.getBody());
        ApacheHttpResponse fileExistResponse = ApacheHttpClientUtil.requestWithoutBody(fileEndpointUrl + "/doesFileExist", HttpMethod.GET, fileExistQueryParam, getBasicHeaders());
        assertEquals("true", fileExistResponse.getBody());
        ApacheHttpResponse deleteResponse = ApacheHttpClientUtil.requestWithoutBody(fileEndpointUrl + "/deleteFile", HttpMethod.DELETE, fileExistQueryParam, getBasicHeaders());
        assertEquals(HttpStatus.OK.value(), deleteResponse.getStatusCode());
    }
}
