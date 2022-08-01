package com.taogen.springiocbyannotation;

import org.springframework.stereotype.Repository;

@Repository("MyBean")
public class MyBean
{
    private String name;
    public MyBean() {}
    public MyBean(String name)
    {
        this.name = name;
    }
    public void sayHello()
    {
        System.out.println("hello by " + name);
    }
}
