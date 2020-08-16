package com.taogen.example.mybatis.annotation.service;

import com.taogen.example.mybatis.annotation.entity.User;

/**
 * @author Taogen
 */
public interface UserService {
    int save(User user);

    int saveSpecifyId(User user);

    int remove(Integer id);

    int update(User user);

    User select(Integer id);
}
