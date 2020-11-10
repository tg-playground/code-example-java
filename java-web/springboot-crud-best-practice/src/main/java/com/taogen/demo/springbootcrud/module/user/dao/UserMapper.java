package com.taogen.demo.springbootcrud.module.user.dao;

import com.taogen.demo.springbootcrud.core.persistence.mapper.CrudMapper;
import com.taogen.demo.springbootcrud.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends CrudMapper<User> {
}