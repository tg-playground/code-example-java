package com.taogen.example.springframework.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Taogen
 */
@RestController
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello" + new Date();
    }
}
