package com.taogen.example.mybatis.xml.service;

import com.taogen.example.mybatis.xml.entity.User;
import com.taogen.example.mybatis.xml.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = new UserServiceImpl();
    @Test
    public void deleteByPrimaryKey() {
        int deleteId = 99;
        if (userService.selectByPrimaryKey(deleteId) == null) {
            userService.insert(new User(deleteId, "beforeDelete", "Taogen", "Jia", 18));
        }
        assertEquals(1, userService.deleteByPrimaryKey(deleteId));
    }

    @Test
    public void insert() {
        int insertId = 98;
        User user = new User(insertId, "Insert " + new Date(), "Taogen", "Jia", 18 );
        assertEquals(1, userService.insert(user));
        assertNotNull(userService.selectByPrimaryKey(insertId));
        userService.deleteByPrimaryKey(insertId);
    }

    @Test
    public void insertSelective() {
        assertEquals(1, userService.insertSelective(new User("insert " + new Date(), "Taogen", "Jia", 18)));
    }

    @Test
    public void selectByPrimaryKey() {
        int userId = 97;
        if (userService.selectByPrimaryKey(userId) == null) {
            userService.insert(new User(userId, "before selectByKey", "Taogen", "Jia", 18));
        }
        assertNotNull(userService.selectByPrimaryKey(userId));
    }

    @Test
    public void updateByPrimaryKeySelective() {
        int userId = 96;
        User user = new User(userId, "before updateByKeySelective", "Taogen", "Jia", 18);
        if (userService.selectByPrimaryKey(userId) == null) {
            userService.insert(user);
        }
        String updateName = "updateByKeySelective" + new Date();
        user.setName(updateName);
        user.setFirstName(null);
        userService.updateByPrimaryKeySelective(user);
        User selectUser = userService.selectByPrimaryKey(userId);
        assertNotNull(selectUser);
        assertEquals(updateName, selectUser.getName());
        assertNotNull(selectUser.getFirstName());
    }

    @Test
    public void updateByPrimaryKey() {
        int userId = 95;
        User user = new User(userId, "before updateByKey", "Taogen", "Jia", 18);
        if (userService.selectByPrimaryKey(userId) == null) {
            userService.insert(user);
        }
        String updateName = "updateByKeySelective" + new Date();
        user.setName(updateName);
        user.setFirstName(null);
        userService.updateByPrimaryKey(user);
        User selectUser = userService.selectByPrimaryKey(userId);
        assertNotNull(selectUser);
        assertEquals(updateName, selectUser.getName());
        assertNull(selectUser.getFirstName());
    }
}