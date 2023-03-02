package com.taogen.demo.springjdbctemplate.multids.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

/**
 * @author Taogen
 */
@Configuration
public class TransactionConfig {

    @Bean
    @Primary
    public ChainedTransactionManager chainedTransactionManager(
            @Qualifier("db1Tx") PlatformTransactionManager db1Tx,
            @Qualifier("db2Tx") PlatformTransactionManager db2Tx
    ) {
        return new ChainedTransactionManager(db1Tx, db2Tx);
    }
}
