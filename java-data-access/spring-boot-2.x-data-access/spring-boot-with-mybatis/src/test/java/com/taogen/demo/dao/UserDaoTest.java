package com.taogen.demo.dao;

import com.taogen.demo.common.impl.BaseUserCrudTest;
import com.taogen.demo.entity.User;
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
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testListEntities(userDao::listAllUsers);
    }

    @Test
    void getUser() {
        User fetchUser = userDao.getUser(addRandomEntity(userDao::saveUser, User::getUserId).getUserId());
        assertNotNull(fetchUser);
        assertNotNull(fetchUser.getUserId());
    }

    @Test
    void saveUser() {
        User fetchUser = userDao.getUser(addRandomEntity(userDao::saveUser, User::getUserId).getUserId());
        log.info("fetchUser: {}", fetchUser);
        assertNotNull(fetchUser);
    }

    @Test
    void updateUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        String newUserName = "new-test-name" + System.currentTimeMillis();
        user.setUserName(newUserName);
        int result = userDao.updateUser(user);
        assertEquals(1, result);
        User fetchUser = userDao.getUser(user.getUserId());
        assertEquals(newUserName, fetchUser.getUserName());
    }

    @Test
    void deleteUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testDeleteEntityById(userDao::deleteUser, user.getUserId(), userDao::getUser);
    }
}
