package com.taogen.example.mybatis.annotation;

import com.taogen.example.mybatis.annotation.entity.User;
import com.taogen.example.mybatis.annotation.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

/**
 * @author Taogen
 */
public class Main {
    private static final Logger logger = LogManager.getLogger();

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWD = "root";

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKey(1);
            user.setName("updateName");
            logger.debug("user is {}", user);
            userMapper.updateByPrimaryKey(user);
            logger.debug("Update user is {}", user);
            session.commit();
        }
    }

    private static SqlSessionFactory buildSqlSessionFactory(){
        DataSource dataSource = new PooledDataSource(DRIVER_CLASS, URL, USER, PASSWD);
        Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(configuration);
    }
}
