package com.taogen.springiocbyxml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean bean = (MyBean) beanFactory.getBean("MyBean");
        bean.sayHello();
    }
}
