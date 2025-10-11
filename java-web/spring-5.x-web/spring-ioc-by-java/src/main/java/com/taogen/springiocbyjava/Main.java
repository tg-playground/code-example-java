package com.taogen.springiocbyjava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        Class<AppConfig> configurationClass = AppConfig.class;
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(configurationClass);
        MyBean myBean = ctx.getBean(MyBean.class);
        myBean.sayHello();
    }
}
