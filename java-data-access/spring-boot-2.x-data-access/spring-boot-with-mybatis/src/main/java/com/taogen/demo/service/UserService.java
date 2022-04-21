package com.taogen.demo.service;


import com.taogen.demo.entity.User;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserService {
    List<User> listAllUsers();

    User getUser(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
