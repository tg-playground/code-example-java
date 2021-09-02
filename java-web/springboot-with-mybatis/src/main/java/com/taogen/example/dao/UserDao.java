package com.taogen.example.dao;

import com.taogen.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Taogen
 */
@Mapper
public interface UserDao {
    List<User> listAllUsers();

    User getUser(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
