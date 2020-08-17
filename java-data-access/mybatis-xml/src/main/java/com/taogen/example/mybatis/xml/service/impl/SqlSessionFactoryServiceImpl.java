package com.taogen.example.mybatis.xml.service.impl;

import com.taogen.example.mybatis.xml.service.SqlSessionFactoryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Taogen
 */
public class SqlSessionFactoryServiceImpl implements SqlSessionFactoryService {

    private static final Logger logger = LogManager.getLogger();

    private static SqlSessionFactory sqlSessionFactory;

    static {
        if (sqlSessionFactory == null) {
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException e) {
                logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
