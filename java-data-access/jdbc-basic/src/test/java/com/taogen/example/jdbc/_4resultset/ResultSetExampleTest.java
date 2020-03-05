package com.taogen.example.jdbc._4resultset;

import com.taogen.example.jdbc._3statement.StatementUtil;
import com.taogen.example.jdbc.constant.StatementSQL;
import org.junit.Test;

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
        // prepare: delete and insert data
        Statement statement = StatementUtil.getStatement();
        statement.executeUpdate(StatementSQL.DELETE_SQL);
        statement.executeUpdate(StatementSQL.INSERT_SQL);

        // update row in result set
        String newname = "update-in-result-set";
        long beforeUpdateCount = ResultSetUtil.getCountFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + newname + "'"));
        assertEquals(0L, beforeUpdateCount);
        resultSetExample.updateAllRowsInResultSet(newname);
        long afterUpdateCount = ResultSetUtil.getCountFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + newname + "'"));
        assertTrue(afterUpdateCount > 0);
    }

    @Test
    public void insertNewRowInResultSet() throws SQLException {
        Statement statement = StatementUtil.getStatement();
        statement.executeUpdate(StatementSQL.DELETE_SQL);

        String name = "insert-in-result-set";
        resultSetExample.insertNewRowInResultSet(name);
        long afterInsertCount = ResultSetUtil.getCountFromResultSet(statement.executeQuery("select count(*) as count from test where name='" + name + "'"));
        assertTrue(afterInsertCount > 0);
    }
}