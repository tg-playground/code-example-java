package com.taogen.example.jdbc.ii.datasource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithC3P0ExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(new DataSourceWithC3P0Example().getConnection());
    }
}