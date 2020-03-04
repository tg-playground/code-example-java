package com.taogen.example.jdbc._1hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JDBCHello {
    private static final Logger logger = LogManager.getLogger();

    public static void hello() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String password = "root";
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("drop table if exists user");
            statement.execute("create table if not exists user(id int not null primary key auto_increment, name varchar(64) not null, age int null)");
            statement.execute("insert into user (name, age) values ('Tom', 18)");
            ResultSet resultSet = statement.executeQuery("select * from user");
            logger.debug("connection is {}", connection);
            while (resultSet.next()) {
                logger.debug("name is {}, age is {}", resultSet.getString(2), resultSet.getString(3));
            }
        }
    }
}
