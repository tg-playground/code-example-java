package com.taogen.example.jdbc._2connection_datasource;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithDBCPExampleTest {

    @Test
    public void getConnection() throws IOException, SQLException {
        assertNotNull(new DataSourceWithDBCPExample().getConnection());
    }
}