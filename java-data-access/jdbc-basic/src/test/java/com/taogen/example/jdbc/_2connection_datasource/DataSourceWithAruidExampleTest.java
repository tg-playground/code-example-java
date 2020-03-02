package com.taogen.example.jdbc._2connection_datasource;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithAruidExample;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataSourceWithAruidExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(DataSourceWithAruidExample.getConnection());
    }
}