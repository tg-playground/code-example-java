package com.taogen.example.jdbc.iv.resultset;

import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResultSetExampleTest {

    private ResultSetExample resultSetExample = new ResultSetExample();

    @Test
    public void printResultSet() {
        assertTrue(resultSetExample.printResultSet());
    }

    @Test
    public void updateAllRowsInResultSet() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            // prepare: delete and insert data
            statement.executeUpdate(StatementSql.DELETE_SQL);
            statement.executeUpdate(StatementSql.INSERT_SQL);

            // update row in result set
            String newname = "update-in-result-set";
            long beforeUpdateCount = ResultSetUtil.getCountFiledFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + newname + "'"));
            assertEquals(0L, beforeUpdateCount);
            resultSetExample.updateAllRowsInResultSet(newname);
            long afterUpdateCount = ResultSetUtil.getCountFiledFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + newname + "'"));
            assertTrue(afterUpdateCount > 0);
        }
    }

    @Test
    public void insertNewRowInResultSet() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(StatementSql.DELETE_SQL);

            String name = "insert-in-result-set";
            resultSetExample.insertNewRowInResultSet(name);
            long afterInsertCount = ResultSetUtil.getCountFiledFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + name + "'"));
            assertTrue(afterInsertCount > 0);
        }
    }
}