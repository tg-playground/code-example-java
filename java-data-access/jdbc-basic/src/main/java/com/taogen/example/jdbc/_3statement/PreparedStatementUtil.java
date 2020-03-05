package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementUtil {

    private static final Logger logger = LogManager.getLogger();

    private PreparedStatementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void executeDdlSQL(String sql) throws SQLException {
        getPreparedStatement(sql).execute();
    }

    public static int executeDmlSql(String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setPreparedStatementParams(preparedStatement, objects);
        return preparedStatement.executeUpdate();
    }

    public static ResultSet executeDqlSql(String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setPreparedStatementParams(preparedStatement, objects);
        return preparedStatement.executeQuery();
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return ConnectionUtil.getConnection().prepareStatement(sql);
    }

    public static void setPreparedStatementParams(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        for (int i = 1; i <= objects.length; i++) {
            logger.debug("parameter {} is: {}", i, objects[i - 1]);
            preparedStatement.setObject(i, objects[i - 1]);
        }
    }
}
