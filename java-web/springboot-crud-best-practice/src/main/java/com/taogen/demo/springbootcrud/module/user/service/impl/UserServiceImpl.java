package com.taogen.demo.springbootcrud.module.user.service.impl;

import com.taogen.demo.springbootcrud.core.web.service.AbstractCrudService;
import com.taogen.demo.springbootcrud.module.user.dao.UserMapper;
import com.taogen.demo.springbootcrud.module.user.entity.User;
import com.taogen.demo.springbootcrud.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class UserServiceImpl
        extends AbstractCrudService<UserMapper, User>
        implements UserService {

    @Autowired
    @Override
    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }
}
