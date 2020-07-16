package com.taogen.springiocbyjava;

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
