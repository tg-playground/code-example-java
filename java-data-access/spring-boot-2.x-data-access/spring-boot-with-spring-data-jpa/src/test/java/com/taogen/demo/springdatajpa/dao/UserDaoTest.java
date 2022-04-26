package com.taogen.demo.springdatajpa.dao;

import com.taogen.demo.springdatajpa.common.BaseUserCrudTest;
import com.taogen.demo.springdatajpa.entity.User;
import com.taogen.demo.springdatajpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class UserDaoTest extends BaseUserCrudTest {

    @Resource
    private UserRepository userDao;

    @Test
    void listAllUsers() {
        addRandomEntityJpa(userDao::save, User::getUserId);
        testListEntitiesJpa(userDao::findAll, PageRequest.of(0, 10));
    }

    @Test
    void getUser() {
        User user = addRandomEntityJpa(userDao::save, User::getUserId);
        testGetEntityByIdJpa(userDao::findById, user.getUserId());
    }

    @Test
    void saveUser() {
        addRandomEntityJpa(userDao::save, User::getUserId);
    }

    @Test
    void updateUser() {
        User user = addRandomEntityJpa(userDao::save, User::getUserId);
        testUpdateEntityByIdJpa(userDao::findById, user.getUserId(),
                User::setUserName, userDao::save, User::getUserName);
    }

    @Test
    void deleteUser() {
        User user = addRandomEntityJpa(userDao::save, User::getUserId);
        testDeleteEntityByIdJpa(userDao::deleteById, user.getUserId(), userDao::findById);
    }

    @Test
    void updateByParam() {
        User user = addRandomEntityJpa(userDao::save, User::getUserId);
        String userName = "test" + System.currentTimeMillis();
        String userEmail = "test" + System.currentTimeMillis() + "@123.com";
        int result = userDao.updateByParam(user.getUserId(), userName, userEmail);
        assertEquals(1, result);
        Optional<User> fetchUser = userDao.findById(user.getUserId());
        log.info("fetchUser = {}", fetchUser);
        assertNotNull(fetchUser);
        assertTrue(fetchUser.isPresent());
        assertEquals(user.getUserId(), fetchUser.get().getUserId());
        assertEquals(userName, fetchUser.get().getUserName());
        assertEquals(userEmail, fetchUser.get().getUserEmail());
    }

    @Test
    void findByParams() {
        User user = new User();
        String userName = "name-spring-data-jpa" + System.currentTimeMillis();
        String userEmail = "email-spring-data-jpa" + System.currentTimeMillis() + "@123.com";
        user.setUserName(userName);
        user.setUserPassword("123456");
        user.setUserEmail(userEmail);
        userDao.save(user);
        log.info("user: {}", user);
        List<User> userList = userDao.findByParams("spring-data-jpa", "spring-data-jpa");
        log.info("userList = {}", userList);
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
    }
}
