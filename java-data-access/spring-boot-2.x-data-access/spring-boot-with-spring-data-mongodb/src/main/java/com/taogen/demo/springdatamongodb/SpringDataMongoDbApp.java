package com.taogen.demo.springdatamongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Taogen
 */
@SpringBootApplication
@EnableMongoRepositories
public class SpringDataMongoDbApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongoDbApp.class, args);
    }
}
