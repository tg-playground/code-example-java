package com.taogen.example.service.impl;

import com.taogen.example.dao.UserDao;
import com.taogen.example.entity.User;
import com.taogen.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Taogen
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }

    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        userDao.updateUser(user);
        return userDao.getUser(user.getUserId());
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

}
