package com.taogen.demo.service.impl;

import com.taogen.demo.common.impl.BaseUserCrudTest;
import com.taogen.demo.entity.User;
import com.taogen.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
class UserServiceImplTest extends BaseUserCrudTest {

    @Autowired
    private UserService userService;

    @Test
    void listAllUsers() {
        addRandomEntity(userService::saveUser, User::getUserId);
        testListEntities(userService::listAllUsers);
    }

    @Test
    void getUser() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
        User user = addRandomEntity(userService::saveUser, User::getUserId);
        testDeleteEntityById(userService::deleteUser, user.getUserId(), userService::getUser);
    }
}
