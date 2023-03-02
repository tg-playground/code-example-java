package com.taogen.springiocwithservlet.bean;

public class MyBean
{
    private String name;
    public MyBean() {}
    public MyBean(String name)
    {
        this.name = name;
    }
    public String sayHello()
    {
        System.out.println("hello by " + name);
        return "hello by " + name;
    }
}
