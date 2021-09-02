package com.taogen.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = "com.taogen.example.dao")
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
