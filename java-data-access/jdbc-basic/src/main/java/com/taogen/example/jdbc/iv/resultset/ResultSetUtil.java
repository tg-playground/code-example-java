package com.taogen.example.jdbc.iv.resultset;

import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUtil {

    private ResultSetUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int getResultSetSize(ResultSet resultSet) throws SQLException {
        int count = 0;
        if (resultSet != null) {
            resultSet.last();
            count = resultSet.getRow();
            resultSet.beforeFirst();
        }
        return count;
    }

    public static long getCountFiledFromResultSet(ResultSet resultSet) throws SQLException {
        long count = -1;
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }

    public static ResultSet getUpdatableResultSet() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement.executeQuery(StatementSql.SELECT_SQL);
    }
}
