package com.taogen.example.jdbc.i.hello;

import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Taogen
 */
public class JdbcHello {
    private static final Logger logger = LogManager.getLogger();

    public boolean hello() {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String password = "root";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()
        ) {
            logger.debug("connection is {}", connection);
            statement.execute("drop table if exists user");
            statement.execute("create table if not exists user(id int not null primary key auto_increment, name varchar(64) not null, age int null)");
            int insertCount = statement.executeUpdate("insert into user (name, age) values ('Tom', 18), ('John', 21)");
            logger.debug("insert {} row(s)", insertCount);
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                logger.debug("name is {}, age is {}", resultSet.getString(2), resultSet.getString(3));
            }
            return true;
        } catch (Exception e) {
            LoggerUtil.loggerError(logger, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LoggerUtil.loggerError(logger, e);
                }
            }
        }
        return false;
    }
}
