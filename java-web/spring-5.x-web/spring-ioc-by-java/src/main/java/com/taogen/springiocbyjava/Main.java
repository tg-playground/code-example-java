package com.taogen.springiocbyjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyBean myBean = ctx.getBean(MyBean.class);
        myBean.sayHello();
    }
}
