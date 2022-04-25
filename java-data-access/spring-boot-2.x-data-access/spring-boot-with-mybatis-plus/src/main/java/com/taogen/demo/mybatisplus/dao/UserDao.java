package com.taogen.demo.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taogen.demo.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Taogen
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
