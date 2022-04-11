package com.taogen.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Taogen
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/")
    public String hello(@RequestParam String name) {
        return helloService.sayHello(name) + " - " + new Date();
    }

}
