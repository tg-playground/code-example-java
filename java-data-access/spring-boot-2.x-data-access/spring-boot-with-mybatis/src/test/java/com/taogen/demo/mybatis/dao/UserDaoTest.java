package com.taogen.demo.mybatis.dao;

import com.taogen.demo.common.vo.Page;
import com.taogen.demo.mybatis.common.BaseUserCrudTest;
import com.taogen.demo.mybatis.entity.Address;
import com.taogen.demo.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.List;

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
    void testListUsersWithAddressesWithPage() {
        List<User> userPage1 = userDao.listUsersWithAddresses(new Page(1, 2), null, null);
        log.info("Page 1 of users: {}", userPage1);
        List<User> userPage2 = userDao.listUsersWithAddresses(new Page(2, 2), null, null);
        log.info("Page 2 of users: {}", userPage2);
    }

    @Test
    void testListUsersWithAddressesWithParams() {
        Address address = new Address();
        address.setCity("city1");
        List<User> userPage1 = userDao.listUsersWithAddresses(new Page(1, 2), null, address);
        log.info("Param 1 - users: {}", userPage1);
        User user = new User();
        user.setUserName("user");
        List<User> userPage2 = userDao.listUsersWithAddresses(new Page(1, 2), user, null);
        log.info("Param 2 - users: {}", userPage2);
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
