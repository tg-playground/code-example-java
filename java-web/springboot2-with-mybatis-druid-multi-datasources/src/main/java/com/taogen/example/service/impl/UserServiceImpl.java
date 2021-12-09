package com.taogen.example.service.impl;

import com.taogen.example.entity.User;
import com.taogen.example.mapper.master.UserMapper;
import com.taogen.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Taogen
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryList() {
        return userMapper.queryList();
    }
}
