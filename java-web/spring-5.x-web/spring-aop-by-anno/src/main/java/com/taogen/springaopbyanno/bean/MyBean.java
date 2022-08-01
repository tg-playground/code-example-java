package com.taogen.springaopbyanno.bean;

import org.springframework.stereotype.Component;

@Component
public class MyBean
{
    public String sayHello()
    {
        System.out.println("sayHello()...");
        return "sayhello()";
    }
}
