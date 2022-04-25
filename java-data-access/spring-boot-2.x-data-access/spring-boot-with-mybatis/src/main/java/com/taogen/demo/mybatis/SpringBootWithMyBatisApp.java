package com.taogen.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Taogen
 */
@SpringBootApplication
@MapperScan(value = "com.taogen.demo.mybatis.dao")
public class SpringBootWithMyBatisApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootWithMyBatisApp.class, args);
    }
}
