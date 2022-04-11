package com.taogen.demo;

import org.springframework.stereotype.Repository;

/**
 * @author Taogen
 */
@Repository
public class HelloDao {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
