package com.taogen.example.config.distributedtransaction.atomikos;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Taogen
 */
@Configuration
@Profile("distributed-transaction-atomikos")
@MapperScan(basePackages = "com.taogen.example.mapper.slave",
        sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class AtomikosSlaveDataSourceMyBatisConfig {

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(
            @Qualifier("distributedSlaveDataSource") DataSource slaveDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slaveDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/slave/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(
            @Qualifier("slaveSqlSessionFactory") SqlSessionFactory slaveSqlSessionFactory) {
        return new SqlSessionTemplate(slaveSqlSessionFactory);
    }
}
