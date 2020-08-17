package com.taogen.example.mybatis.annotation.service;

import com.taogen.example.mybatis.annotation.entity.User;
import com.taogen.example.mybatis.annotation.service.UserService;
import com.taogen.example.mybatis.annotation.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void save() {
        assertEquals(1, userService.save(new User("newUser " + new Date(), 12)));
    }

    @Test
    public void remove() {
        int removeUserId = 100;
        if (userService.select(removeUserId) == null){
            userService.saveSpecifyId(new User(removeUserId, "newUser "+new Date(), 18));
        }
        assertEquals(1, userService.remove(removeUserId));
    }

    @Test
    public void update() {
        assertEquals(1, userService.update(new User(1, "updateName " + new Date(), 18)));
    }

    @Test
    public void select() {
        assertEquals(1, userService.select(1).getId().intValue());
    }
}