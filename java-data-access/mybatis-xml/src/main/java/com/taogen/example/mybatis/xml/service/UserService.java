package com.taogen.example.mybatis.xml.service;

import com.taogen.example.mybatis.xml.entity.User;

/**
 * @author Taogen
 */
public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
