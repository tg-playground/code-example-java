package com.taogen.example.mybatis.sqlmap.annotations.service.impl;

import com.taogen.example.mybatis.sqlmap.annotations.service.SqlSessionFactoryService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SqlSessionFactoryServiceImplTest {

    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    @Test
    public void getSqlSessionFactory() {
        assertNotNull(sqlSessionFactoryService.getSqlSessionFactory());
    }
}