package com.taogen.springiocbyannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyBean.class);
        ctx.refresh();
        MyBean myBean = (MyBean) ctx.getBean("MyBean");
        myBean.sayHello();
    }
}
