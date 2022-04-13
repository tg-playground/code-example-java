package com.taogen.demo.service;

import com.taogen.demo.domain.User;
import com.taogen.demo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    void queryList() {
        List<User> users = userService.queryList(null);
        System.out.println(users);
    }

    @Test
    void getEntity() {
    }

    @Test
    void saveEntity() {
    }

    @Test
    void updateEntity() {
    }

    @Test
    void deleteByIds() {
    }
}
