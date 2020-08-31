package com.taogen.example.mybatis.sqlmap.annotations.service.impl;

import com.taogen.example.mybatis.sqlmap.annotations.constant.JdbcConfig;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.DepartmentMapper;
import com.taogen.example.mybatis.sqlmap.annotations.mapper.EmployeeMapper;
import com.taogen.example.mybatis.sqlmap.annotations.service.SqlSessionFactoryService;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Taogen
 */
public class SqlSessionFactoryServiceImpl implements SqlSessionFactoryService {

    private static final Logger logger = LogManager.getLogger();

    private static SqlSessionFactory sqlSessionFactory;

    static {
        if (sqlSessionFactory == null) {
            Properties properties = null;
            DataSource dataSource = null;
            try {
                properties = getPropertiesByFilePath("mybatis/db.properties");
                String driverClass = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_DRIVER_CLASS));
                String url = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL));
                String userName = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER));
                String password = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD));
                dataSource = new PooledDataSource(driverClass, url, userName, password);
            } catch (IOException e) {
                logger.error(e);
            }
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(DepartmentMapper.class);
            configuration.addMapper(EmployeeMapper.class);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }

    }

    /**
     * @param filepath: relative to classes path
     * @return
     * @throws IOException
     */
    public static Properties getPropertiesByFilePath(String filepath) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filepath);
        try {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        return properties;
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
