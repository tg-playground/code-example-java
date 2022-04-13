package com.taogen.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taogen.demo.controller.UserController;
import com.taogen.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void list() {
        Page<User> page = new Page<>(1, 10);
        Page<User> userPage = userController.list(null, page);
        System.out.println(userPage.getRecords());
    }

    @Test
    void getInfo() {
    }

    @Test
    void add() {
    }

    @Test
    void edit() {
    }

    @Test
    void remove() {
    }
}
