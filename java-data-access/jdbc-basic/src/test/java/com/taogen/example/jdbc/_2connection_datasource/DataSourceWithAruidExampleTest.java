package com.taogen.example.jdbc._2connection_datasource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithAruidExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(new DataSourceWithAruidExample().getConnection());
    }
}