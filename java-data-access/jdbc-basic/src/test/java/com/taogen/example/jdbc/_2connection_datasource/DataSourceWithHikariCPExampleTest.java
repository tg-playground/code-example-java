package com.taogen.example.jdbc._2connection_datasource;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithHikariCPExample;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataSourceWithHikariCPExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(DataSourceWithHikariCPExample.getConnection());
    }
}