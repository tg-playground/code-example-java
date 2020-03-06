package com.taogen.example.jdbc.ii.datasource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithHikaricpExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(new DataSourceWithHikaricpExample().getConnection());
    }
}