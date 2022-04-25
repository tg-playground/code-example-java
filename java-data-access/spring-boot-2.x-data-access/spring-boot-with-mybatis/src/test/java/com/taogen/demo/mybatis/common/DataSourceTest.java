package com.taogen.demo.mybatis.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class DataSourceTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try (Connection connection = session.getConnection()) {
                log.info("connection: {}", connection.toString());
                assertNotNull(connection);
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery("select version()")) {
                        resultSet.next();
                        String version = resultSet.getString(1);
                        assertNotNull(version);
                        log.info("version: {}", version);
                    }
                }
            }
        }
    }
}
