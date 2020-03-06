package com.taogen.example.jdbc.iii.statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class PreparedStatementUtil {

    private static final Logger logger = LogManager.getLogger();

    private PreparedStatementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void executeDdlSql(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();
    }

    public static int executeDmlSql(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        setPreparedStatementParams(preparedStatement, objects);
        return preparedStatement.executeUpdate();
    }

    public static ResultSet executeDqlSql(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        setPreparedStatementParams(preparedStatement, objects);
        return preparedStatement.executeQuery();
    }

    public static PreparedStatement getPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static void setPreparedStatementParams(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        for (int i = 1; i <= objects.length; i++) {
            logger.debug("parameter {} is: {}", i, objects[i - 1]);
            preparedStatement.setObject(i, objects[i - 1]);
        }
    }
}
