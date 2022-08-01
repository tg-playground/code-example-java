package com.taogen.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taogen.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Taogen
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
