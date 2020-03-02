package com.taogen.example.jdbc._2connection_datasource;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithC3P0Example;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataSourceWithC3P0ExampleTest {

    @Test
    public void getConnection() {
        assertNotNull(DataSourceWithC3P0Example.getConnection());
    }
}