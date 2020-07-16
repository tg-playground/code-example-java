package com.taogen.springiocdi.Bean;

public class MyInjectBean implements AbstractBean
{
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MyInjectBean(){}
    public MyInjectBean(String name){this.name = name;}
    @Override
    public String sayHello() {
        String s = "I am Inject Bean. My name is " + this.name;
        System.out.println(s);
        return s;
    }
}
