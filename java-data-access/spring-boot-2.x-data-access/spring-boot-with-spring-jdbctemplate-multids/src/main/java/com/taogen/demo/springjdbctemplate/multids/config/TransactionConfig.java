package com.taogen.demo.springjdbctemplate.multids.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

/**
 * The ChainedTransactionManger is working as expected for:
 * insert -> DB1 -> SUCCESSFUL insert -> DB2 -> ERROR ROLLBACK DB1
 * but not for:
 * insert -> DB1 -> FAIL ROLLBAK DB1 -> DB2 -> SUCCESSFUL
 *
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
