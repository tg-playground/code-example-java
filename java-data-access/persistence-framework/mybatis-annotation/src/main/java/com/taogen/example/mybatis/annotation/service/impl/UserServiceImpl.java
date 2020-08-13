package com.taogen.example.mybatis.annotation.service.impl;

import com.taogen.example.mybatis.annotation.entity.User;
import com.taogen.example.mybatis.annotation.mapper.UserMapper;
import com.taogen.example.mybatis.annotation.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.annotation.service.UserService;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Taogen
 */
public class UserServiceImpl implements UserService {

    SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    @Override
    public int save(User user) {
        try (SqlSession session = sqlSessionFactoryService.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(UserMapper.class).insert(user);
        }
    }

    @Override
    public int saveSpecifyId(User user) {
        try (SqlSession session = sqlSessionFactoryService.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(UserMapper.class).insertWithId(user);
        }
    }

    @Override
    public int remove(Integer id) {
        try (SqlSession session = sqlSessionFactoryService.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(UserMapper.class).deleteByPrimaryKey(id);
        }
    }

    @Override
    public int update(User user) {
        try (SqlSession session = sqlSessionFactoryService.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(UserMapper.class).updateByPrimaryKey(user);
        }
    }

    @Override
    public User select(Integer id) {
        try (SqlSession session = sqlSessionFactoryService.getSqlSessionFactory().openSession()) {
            return session.getMapper(UserMapper.class).selectByPrimaryKey(id);
        }
    }
}
