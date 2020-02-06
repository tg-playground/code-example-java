package com.taogen;

import org.springframework.stereotype.Service;

@Service
public class HelloService
{
    private static final String HELLO_MSG = "Hello World!";

    public String sayHello()
    {
        return HELLO_MSG;
    }
}
