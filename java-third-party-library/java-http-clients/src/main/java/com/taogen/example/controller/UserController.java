package com.taogen.example.controller;

import com.taogen.example.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Taogen
 */
@RestController
@RequestMapping("/users")
public class UserController {
    public static final String RANDOM_TOKEN = "123456";

    public static final String CHINESE_TEST = "中文测试";

    public static final String APP_HEADER_KEY = "my-app-id";
    public static final String APP_HEADER_VALUE = "java-http-clients";

    private Map<Long, User> userMap = new HashMap<>();

    @PostConstruct
    public void init() {
        userMap.put(
                1L, new User(1L, "Tom"));
        userMap.put(
                2L, new User(2L, "Jerry"));
        userMap.put(
                3L, new User(3L, "Jack"));
    }

    @GetMapping
    public Collection<User> getUsers(@RequestParam String token,
                                     @RequestParam String chineseTest,
                                     @RequestHeader HttpHeaders headers) {
        checkRequest(token, headers);
        checkChineseTest(chineseTest);
        return userMap.values();
    }

    private void checkChineseTest(String chineseTest) {
        if (!CHINESE_TEST.equals(chineseTest)) {
            System.out.println("chineseTest is:" + chineseTest);
            throw new IllegalArgumentException("chineseTest is not correct");
        }
    }

    private void checkRequest(String token,
                              HttpHeaders headers) {
        if (!RANDOM_TOKEN.equals(token)) {
            throw new IllegalArgumentException("token is not correct");
        }
        if (headers.get(APP_HEADER_KEY) == null || !headers.get(APP_HEADER_KEY).contains(APP_HEADER_VALUE)) {
            throw new IllegalArgumentException("header is not correct");
        }
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id,
                        @RequestParam String token,
                        @RequestHeader HttpHeaders headers) {
        checkRequest(token, headers);
        User user = userMap.get(id);
        return user;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user,
                           @RequestParam String token,
                           @RequestHeader HttpHeaders headers) {
        checkRequest(token, headers);
        Optional<Long> max = userMap.keySet().stream().max(Long::compareTo);
        user.setId(max.orElseGet(() -> 0L) + 1);
        userMap.put(user.getId(), user);
        return user;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User updateUser(@PathVariable Long id, User user,
                           @RequestParam String token,
                           @RequestHeader HttpHeaders headers) {
        checkRequest(token, headers);
        userMap.put(id, user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id,
                           @RequestParam String token,
                           @RequestHeader HttpHeaders headers) {
        checkRequest(token, headers);
        userMap.remove(id);
    }
}
