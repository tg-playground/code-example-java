package com.taogen.springiocbyxml;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainGeneric {
    public static void main(String[] args) {
        GenericApplicationContext beanFactory = new GenericXmlApplicationContext("applicationContext.xml");
        MyBean bean = (MyBean) beanFactory.getBean("MyBean");
        bean.sayHello();
    }
}
