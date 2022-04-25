package com.taogen.demo.mybatisplus.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taogen.demo.mybatisplus.common.BaseUserCrudTest;
import com.taogen.demo.mybatisplus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserDaoTest extends BaseUserCrudTest {

    @Resource
    private UserDao userDao;

    @Test
    void listAllUsers() {
        addRandomEntity(userDao::insert, User::getUserId);
        Page<User> page = userDao.selectPage(new Page(1, 10), null);
        List<User> records = page.getRecords();
        assertNotNull(records);
        assertFalse(records.isEmpty());
        log.info("list size: {}", records.size());
    }

    @Test
    void getUser() {
        User user = addRandomEntity(userDao::insert, User::getUserId);
        testGetEntityById(userDao::selectById, user.getUserId());
    }

    @Test
    void insert() {
        addRandomEntity(userDao::insert, User::getUserId);
    }

    @Test
    void updateById() {
        User user = addRandomEntity(userDao::insert, User::getUserId);
        testUpdateEntityById(userDao::selectById, user.getUserId(),
                User::setUserName, userDao::updateById, User::getUserName);
    }

    @Test
    void deleteById() {
        User user = addRandomEntity(userDao::insert, User::getUserId);
        testDeleteEntityById(userDao::deleteById, user.getUserId(), userDao::selectById);
    }
}
