package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StatementExampleTest {

    @Before
    public void init() throws SQLException {
        if (StatementExample.checkTableExist() <= 0){
            StatementExample.createTable();
        }
        if (ResultSetUtil.getResultSetCount(StatementExample.select()) <= 0){
            StatementExample.insert();
        }
    }

    @Test
    public void createTable() {
        StatementExample.createTable();
    }

    @Test
    public void insert() {
        assertTrue(StatementExample.insert() > 0);
    }

    @Test
    public void update() {
        assertTrue(StatementExample.update() > 0);
    }

    @Test
    public void select() {
        assertNotNull(StatementExample.select());
    }

    @Test
    public void delete() {
        assertTrue(StatementExample.delete() >= 0);
    }

    @Test
    public void dropTable() {
        StatementExample.dropTable();
    }

    @Test
    public void checkTableExist() {
        StatementExample.createTable();
        assertTrue(StatementExample.checkTableExist() > 0);
    }
}