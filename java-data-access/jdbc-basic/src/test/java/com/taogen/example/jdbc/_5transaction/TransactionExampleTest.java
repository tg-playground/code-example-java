package com.taogen.example.jdbc._5transaction;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionExampleTest {

    @Test
    public void transactionCommit() {
        TransactionExample.transactionCommit();
    }

    @Test
    public void transactionRollback() {
        TransactionExample.transactionRollback();

    }
}