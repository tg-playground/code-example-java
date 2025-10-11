package com.taogen.springiocbyxml;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainGeneric {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");
        MyBean bean = (MyBean) ctx.getBean("MyBean");
        bean.sayHello();
    }
}
