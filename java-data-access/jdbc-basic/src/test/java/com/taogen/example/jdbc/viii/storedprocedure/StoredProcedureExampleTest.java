package com.taogen.example.jdbc.viii.storedprocedure;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StoredProcedureExampleTest {

    private StoredProcedureExample storedProcedureExample = new StoredProcedureExample();

    @Test
    public void createProcedure() throws SQLException {
        String procedureName = storedProcedureExample.createProcedure();
        String dbName = "test";
        String queryProcedureSql = new StringBuilder()
                .append("SELECT count(*) as count FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_TYPE='PROCEDURE' AND ROUTINE_SCHEMA='")
                .append(dbName)
                .append("' AND ROUTINE_NAME='")
                .append(procedureName).append("'")
                .toString();
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryProcedureSql)
        ) {
            assertEquals(1, ResultSetUtil.getCountFiledFromResultSet(resultSet));
        }
    }

    @Test
    public void callProcedure() throws SQLException {
        assertNotNull(storedProcedureExample.callProcedure());
    }
}