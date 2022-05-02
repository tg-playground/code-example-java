package com.taogen.demo.springjdbctemplate.common;

import com.taogen.demo.springjdbctemplate.SpringBootWithJdbcTemplateApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringBootWithJdbcTemplateApp.class})
@Slf4j
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
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
