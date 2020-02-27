package com.taogen.example.jdbc._2connection;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceExampleTest {

    @Test
    public void getConnectionFromDataSoruce() {
        assertNotNull(DataSourceExample.getConnectionFromDataSoruce());
    }
}