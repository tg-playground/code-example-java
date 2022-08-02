package com.taogen.example.mybatis.xml.service;

import com.taogen.example.mybatis.xml.service.SqlSessionFactoryService;
import com.taogen.example.mybatis.xml.service.impl.SqlSessionFactoryServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SqlSessionFactoryServiceTest {

    private SqlSessionFactoryService sqlSessionFactoryService = new SqlSessionFactoryServiceImpl();

    @Test
    public void getSqlSessionFactory() {
        assertNotNull(sqlSessionFactoryService.getSqlSessionFactory());
    }
}