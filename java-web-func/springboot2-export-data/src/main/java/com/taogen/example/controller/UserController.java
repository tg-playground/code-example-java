package com.taogen.example.controller;

import com.taogen.example.entity.User;
import com.taogen.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Taogen
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.list();
    }
}
