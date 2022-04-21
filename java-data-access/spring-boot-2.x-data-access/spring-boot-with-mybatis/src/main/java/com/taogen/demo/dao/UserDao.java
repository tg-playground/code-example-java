package com.taogen.demo.dao;

import com.taogen.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Taogen
 */
@Mapper
public interface UserDao {
    List<User> listAllUsers();

    User getUser(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
