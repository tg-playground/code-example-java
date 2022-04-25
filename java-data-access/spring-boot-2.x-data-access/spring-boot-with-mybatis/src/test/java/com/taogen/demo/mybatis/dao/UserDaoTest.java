package com.taogen.demo.mybatis.dao;

import com.taogen.demo.common.vo.Page;
import com.taogen.demo.mybatis.common.BaseUserCrudTest;
import com.taogen.demo.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class UserDaoTest extends BaseUserCrudTest {

    @Resource
    private UserDao userDao;

    @Test
    void listAllUsers() {
        addRandomEntity(userDao::saveUser, User::getUserId);
        testListEntities(userDao::listUsers, new Page(1, 10), null);
    }

    @Test
    void getUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testGetEntityById(userDao::getUser, user.getUserId());
    }

    @Test
    void saveUser() {
        addRandomEntity(userDao::saveUser, User::getUserId);
    }

    @Test
    void updateUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testUpdateEntityById(userDao::getUser, user.getUserId(),
                User::setUserName, userDao::updateUser, User::getUserName);
    }

    @Test
    void deleteUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testDeleteEntityById(userDao::deleteUser, user.getUserId(), userDao::getUser);
    }
}
