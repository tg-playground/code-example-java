package com.taogen.example.jdbc._5transaction;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionExampleTest {

    private TransactionExample transactionExample = new TransactionExample();

    @Test
    public void transactionCommit() {
        assertTrue(transactionExample.transactionCommit());
    }

    @Test
    public void transactionRollback() {
        assertTrue(transactionExample.transactionRollback());
    }
}