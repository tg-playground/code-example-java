package com.taogen.example.jdbc._1hello;

import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JDBCHello {
    private static final Logger logger = LogManager.getLogger();

    public static void hello() {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String password = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from user");
        ) {
            logger.debug("connection is {}", connection);
            while (resultSet.next()) {
                logger.debug("name is {}, age is {}", resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }
}
