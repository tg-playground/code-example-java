package com.taogen.example.jdbc.ii.datasource;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithDbcpExampleTest {

    @Test
    public void getConnection() throws IOException, SQLException {
        assertNotNull(new DataSourceWithDbcpExample().getConnection());
    }
}