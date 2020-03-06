package com.taogen.example.jdbc.iv.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taogen
 */
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
}
