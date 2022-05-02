package com.taogen.demo.springjdbctemplate.dao.impl;

import com.taogen.demo.common.test.AbstractCrudTest;
import com.taogen.demo.common.vo.Page;
import com.taogen.demo.springjdbctemplate.dao.UserDao;
import com.taogen.demo.springjdbctemplate.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserDaoImplTest extends AbstractCrudTest<User> {
    @Autowired
    private UserDao userDao;

    @Test
    void listUsers() {
        User user = new User();
        user.setUserName("test-name");
        testListEntities(userDao::listUsers, new Page(1, 10), user);
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
        testUpdateEntityById(userDao::getUser, user.getUserId(), User::setUserName,
                userDao::updateUser, User::getUserName);
    }

    @Test
    void deleteUser() {
        User user = addRandomEntity(userDao::saveUser, User::getUserId);
        testDeleteEntityById(userDao::deleteUser, user.getUserId(), userDao::getUser);
    }

    @Override
    protected User buildEntityWithoutId() {
        return new User("test-name" + System.currentTimeMillis(), "test-password", "test-email");
    }
}
