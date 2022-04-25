package com.taogen.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Taogen
 */
@SpringBootApplication
@MapperScan(value = "com.taogen.demo.dao")
public class SpringBootWithMyBatisApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringBootWithMyBatisApp.class, args);
    }
}
