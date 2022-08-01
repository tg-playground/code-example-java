package com.taogen.example.dao;

import com.taogen.example.entity.User;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserDao {
    List<User> listAllUsers();

    User getUser(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
