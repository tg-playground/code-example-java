package com.taogen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("HelloController")
@RequestMapping({"/"})
public class HelloController
{
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String sayHello()
    {
        String message = helloService.sayHello();
        return "{ret_code: 0, ret_msg: " + message + "}";
    }
}
