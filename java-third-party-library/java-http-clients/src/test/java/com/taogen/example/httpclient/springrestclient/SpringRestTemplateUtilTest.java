package com.taogen.example.httpclient.springrestclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taogen.example.controller.UserController;
import com.taogen.example.entity.User;
import com.taogen.example.httpclient.BaseTest;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringRestTemplateUtilTest extends BaseTest {

    public static final String domain = "http://localhost";

    public static final String USER_ENDPOINT = "users";

    public static final String FILE_ENDPOINT = "files";

    @Autowired
    private SpringRestTemplateUtil springRestTemplateUtil;

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
    void getString() {
        String url = new StringBuilder()
                .append(userEndpointUrl)
                .append("/")
                .append(1)
                .toString();
        ResponseEntity<String> response = springRestTemplateUtil.request(
                url, HttpMethod.GET, getBasicParam(), getBasicHeaders(), null, String.class);
        System.out.println(response.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Assumptions.assumingThat(response.getBody() != null, () -> {
            assertEquals(String.class, response.getBody().getClass());
        });
    }

    @Test
    void getObject() {
        String url = new StringBuilder()
                .append(userEndpointUrl)
                .append("/")
                .append(1)
                .toString();
        ResponseEntity<User> response = springRestTemplateUtil.request(
                url, HttpMethod.GET, getBasicParam(), getBasicHeaders(), null, User.class);
        System.out.println(response.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assumptions.assumingThat(response.getBody() != null, () -> {
            assertEquals(User.class, response.getBody().getClass());
        });
    }

    @Test
    void getObjectList() {
        String url = userEndpointUrl;
        ResponseEntity<List<User>> response = springRestTemplateUtil.getObjectList(
                url, getBasicParam(), getBasicHeaders(), null, new ParameterizedTypeReference<List<User>>() {
                });
        System.out.println(response.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Assumptions.assumingThat(response.getBody() != null && response.getBody().size() > 0, () -> {
            assertEquals(User.class, response.getBody().get(0).getClass());
        });
    }

    @Test
    void postWithJson() throws JsonProcessingException {
        String name = "test" + System.currentTimeMillis();
        ResponseEntity<User> response = createUser(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ResponseEntity<User> userResponse = getUserById(response.getBody().getId());
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        assertNotNull(userResponse.getBody());
        assertEquals(name, userResponse.getBody().getName());
    }

    @Test
    void putWithFormUrlEncoded() {
        Long id = 1L;
        User user = new User();
        String newName = "test" + System.currentTimeMillis();
        user.setId(id);
        user.setName(newName);
        ResponseEntity response = updateUserById(user);
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<User> userResponse = getUserById(id);
        System.out.println(userResponse);
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        assertNotNull(userResponse.getBody());
        assertEquals(newName, userResponse.getBody().getName());
    }

    @Test
    void deleteById() throws JsonProcessingException {
        ResponseEntity<User> userResponse = createUser("test" + System.currentTimeMillis());
        Long id = userResponse.getBody().getId();
        assertNotNull(getUserById(id).getBody());
        ResponseEntity response = springRestTemplateUtil.request(
                userEndpointUrl + "/" + id, HttpMethod.DELETE, getBasicParam(), getBasicHeaders(), null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(getUserById(id).getBody());
    }

    @Test
    void postWithFormData() throws FileNotFoundException {
        MultiValueMap<String, String> headers = getBasicHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File file = ResourceUtils.getFile("classpath:static/icon.jpg");
        body.add("file", new FileSystemResource(file));
        ResponseEntity<String> response = springRestTemplateUtil.request(
                fileEndpointUrl + "/upload", HttpMethod.POST, getBasicParam(), headers, body, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // check file is uploaded
        MultiValueMap<String, String> param = getBasicParam();
        param.add("fileUri", response.getBody());
        ResponseEntity<Boolean> responseOfFileExist = springRestTemplateUtil.request(
                fileEndpointUrl + "/doesFileExist", HttpMethod.GET, param, getBasicHeaders(), null, Boolean.class);
        assertTrue(responseOfFileExist.getBody());
        // delete file
        ResponseEntity<String> responseOfDeleteFile = springRestTemplateUtil.request(fileEndpointUrl + "/deleteFile", HttpMethod.DELETE, param, getBasicHeaders(), null, String.class);
        assertEquals(HttpStatus.OK, responseOfDeleteFile.getStatusCode());
    }

    private MultiValueMap<String, String> getBasicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(UserController.APP_HEADER_KEY, UserController.APP_HEADER_VALUE);
        return headers;
    }

    private ResponseEntity<User> getUserById(Long id) {
        ResponseEntity<User> userResponse = springRestTemplateUtil.request(
                userEndpointUrl + "/" + id, HttpMethod.GET, getBasicParam(), getBasicHeaders(), null, User.class);
        return userResponse;
    }

    private ResponseEntity<User> createUser(String name) throws JsonProcessingException {
        String url = userEndpointUrl;
        MultiValueMap headers = getBasicHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setName(name);
        String string = mapper.writeValueAsString(user);
        ResponseEntity<User> response = springRestTemplateUtil.request(
                url, HttpMethod.POST, getBasicParam(), headers, string, User.class);
        return response;
    }

    private ResponseEntity updateUserById(User user) {
        String url = userEndpointUrl + "/" + user.getId();
        MultiValueMap headers = getBasicHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        ResponseEntity response = springRestTemplateUtil.request(
                url, HttpMethod.PUT, getBasicParam(), headers,
                convertObjectToMultiValueMap(user), User.class);
        return response;
    }

}
