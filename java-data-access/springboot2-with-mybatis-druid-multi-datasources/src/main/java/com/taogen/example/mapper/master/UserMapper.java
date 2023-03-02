package com.taogen.example.mapper.master;

import com.taogen.example.entity.User;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserMapper {
    List<User> queryList();

    int insert(User user);
}
