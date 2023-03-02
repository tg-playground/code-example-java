package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class ConfigClientApp {
    @Value("${test.greeting}")
    private String testGreeting;

    @Value("${test.msg}")
    private String test2Msg;

    @GetMapping("/")
    public String getMsg() {
        // dev: Hi developer! How is your coding going?
        // prod: Hi there! How are you doing?
        return testGreeting + " " + test2Msg;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(testGreeting);
        System.out.println(test2Msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class, args);
    }
}
