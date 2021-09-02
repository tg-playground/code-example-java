package com.taogen.example.service;

import com.taogen.example.entity.User;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserService {
    List<User> listAllUsers();

    User getUser(Integer id);

    void saveUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id);
}
