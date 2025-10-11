package com.taogen.springiocbyxml;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRefreshable
{
    public static void main(String[] args)
    {
        AbstractRefreshableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean bean = (MyBean) ctx.getBean("MyBean");
        bean.sayHello();
    }
}
