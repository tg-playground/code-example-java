package com.taogen.demo.springdatajpa.dao;

import com.taogen.demo.springdatajpa.common.BaseUserCrudTest;
import com.taogen.demo.springdatajpa.entity.User;
import com.taogen.demo.springdatajpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

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
}
