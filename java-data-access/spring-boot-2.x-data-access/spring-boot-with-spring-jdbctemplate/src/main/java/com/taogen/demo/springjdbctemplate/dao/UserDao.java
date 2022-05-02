package com.taogen.demo.springjdbctemplate.dao;

import com.taogen.demo.common.vo.Page;
import com.taogen.demo.springjdbctemplate.entity.User;

import java.util.List;

/**
 * @author Taogen
 */
public interface UserDao {
    List<User> listUsers(Page page, User user);

    User getUser(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
