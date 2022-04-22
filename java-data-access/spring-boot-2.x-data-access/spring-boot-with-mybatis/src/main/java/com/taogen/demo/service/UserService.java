package com.taogen.demo.service;


import com.taogen.demo.entity.User;
import com.taogen.demo.vo.Page;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserService {
    List<User> listUsers(Page page, User user);

    User getUser(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
