package com.taogen.demo.mybatis.service.impl;

import com.taogen.demo.mybatis.common.BaseUserCrudTest;
import com.taogen.demo.mybatis.entity.User;
import com.taogen.demo.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class UserServiceImplTest extends BaseUserCrudTest {

    @Autowired
    private UserService userService;

    @Test
    void listUsers() {
        addRandomEntity(userService::saveUser, User::getUserId);
        testListEntities(userService::listUsers);
    }

    @Test
    void getUser() {
        User user = addRandomEntity(userService::saveUser, User::getUserId);
        testGetEntityById(userService::getUser, user.getUserId());
    }

    @Test
    void saveUser() {
        addRandomEntity(userService::saveUser, User::getUserId);
    }

    @Test
    void updateUser() {
        User user = addRandomEntity(userService::saveUser, User::getUserId);
        testUpdateEntityById(userService::getUser, user.getUserId(),
                User::setUserName, userService::updateUser, User::getUserName);
    }

    @Test
    void deleteUser() {
        User user = addRandomEntity(userService::saveUser, User::getUserId);
        testDeleteEntityById(userService::deleteUser, user.getUserId(), userService::getUser);
    }
}
