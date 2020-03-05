package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementUtil {

    private StatementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void executeDdlSQL(String sql) throws SQLException {
        getStatement().execute(sql);
    }

    public static int executeDmlSql(String sql) throws SQLException {
        return getStatement().executeUpdate(sql);
    }

    public static ResultSet executeDqlSql(String sql) throws SQLException {
        return getStatement().executeQuery(sql);
    }

    public static Statement getStatement() throws SQLException {
        return ConnectionUtil.getConnection().createStatement();
    }
}
