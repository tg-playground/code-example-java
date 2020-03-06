package com.taogen.example.jdbc.ii.datasource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithHikariCPExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(new DataSourceWithHikariCPExample().getConnection());
    }
}