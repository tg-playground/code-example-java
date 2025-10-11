package com.taogen.springiocbyxml;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRefreshable
{
    public static void main(String[] args)
    {
        AbstractRefreshableApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean bean = (MyBean) beanFactory.getBean("MyBean");
        bean.sayHello();
    }
}
