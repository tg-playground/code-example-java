package com.taogen.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taogen.example.entity.User;
import com.taogen.example.mapper.master.UserMapper;
import com.taogen.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}
