package com.taogen.springiocbyjava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class MainV2 {
    public static void main(String[] args) {
        String basePackage = "com.taogen.springiocbyjava";
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(basePackage);
        MyBean myBean = ctx.getBean(MyBean.class);
        myBean.sayHello();
    }
}
