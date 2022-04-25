package com.taogen.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taogen.demo.mybatisplus.dao.UserDao;
import com.taogen.demo.mybatisplus.entity.User;
import com.taogen.demo.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
