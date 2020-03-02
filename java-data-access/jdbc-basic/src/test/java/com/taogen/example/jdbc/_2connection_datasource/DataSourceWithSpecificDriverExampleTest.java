package com.taogen.example.jdbc._2connection_datasource;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DataSourceWithSpecificDriverExampleTest {

    @Test
    public void getConnectionFromDataSoruce() {
        assertNotNull(DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce());
    }
}