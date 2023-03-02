package com.taogen.example.mybatis.sqlmap.annotations.service.impl;

import com.taogen.example.mybatis.sqlmap.annotations.service.SqlSessionFactoryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SqlSessionFactoryServiceImplTest {

    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    @Test
    public void getSqlSessionFactory() {
        assertNotNull(sqlSessionFactoryService.getSqlSessionFactory());
    }
}