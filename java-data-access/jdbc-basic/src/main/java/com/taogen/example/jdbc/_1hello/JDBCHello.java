package com.taogen.example.jdbc._1hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JDBCHello {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC", user = "root", password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        logger.debug("connection is {}", connection);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()){
            logger.debug("name is {}, age is {}", resultSet.getString(2), resultSet.getString(1));
        }
    }
}
