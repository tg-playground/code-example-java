package com.taogen.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void sayHello() {
        assertEquals("Hello World", helloService.sayHello("World"));
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
