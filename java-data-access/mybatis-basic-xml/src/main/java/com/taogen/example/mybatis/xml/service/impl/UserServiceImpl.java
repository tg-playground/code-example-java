package com.taogen.example.mybatis.xml.service.impl;

import com.taogen.example.mybatis.xml.entity.User;
import com.taogen.example.mybatis.xml.mapper.UserMapper;
import com.taogen.example.mybatis.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.xml.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author Taogen
 */
public class UserServiceImpl implements UserService {
    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactoryService.getSqlSessionFactory();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession(true)){
            return sqlSession.getMapper(UserMapper.class).deleteByPrimaryKey(id);
        }
    }

    @Override
    public int insert(User record) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession(true)){
            return sqlSession.getMapper(UserMapper.class).insert(record);
        }
    }

    @Override
    public int insertSelective(User record) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession(true)){
            return sqlSession.getMapper(UserMapper.class).insertSelective(record);
        }
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()){
            return sqlSession.getMapper(UserMapper.class).selectByPrimaryKey(id);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession(true)){
            return sqlSession.getMapper(UserMapper.class).updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int updateByPrimaryKey(User record) {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession(true)){
            return sqlSession.getMapper(UserMapper.class).updateByPrimaryKey(record);
        }
    }
}
