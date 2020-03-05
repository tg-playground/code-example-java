package com.taogen.example.jdbc._2connection_datasource;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithSpecificDriverExampleTest {

    @Test
    public void getConnectionFromDataSoruce() {
        assertNotNull(new DataSourceWithSpecificDriverExample().getConnectionFromDataSoruce());
    }
}