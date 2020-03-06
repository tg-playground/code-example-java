package com.taogen.example.jdbc.iii.statement;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Taogen
 */
public class StatementUtil {

    private StatementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void executeDdlSql(Statement statement, String sql) throws SQLException {
        statement.execute(sql);
    }

    public static int executeDmlSql(Statement statement, String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public static ResultSet executeDqlSql(Statement statement, String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public static Statement getStatementWithUpdatableResult(Connection connection) throws SQLException {
        return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
}
