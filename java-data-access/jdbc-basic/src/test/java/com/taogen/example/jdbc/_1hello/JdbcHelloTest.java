package com.taogen.example.jdbc._1hello;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JdbcHelloTest {

    @Test
    public void hello() {
        assertTrue(new JdbcHello().hello());
    }

}