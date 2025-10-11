package com.taogen.springiocbyannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class MainV2 {
    public static void main(String[] args) {
        String basePackage = "com.taogen.springiocbyannotation";
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(basePackage);
        MyBean myBean = (MyBean) ctx.getBean("MyBean");
        myBean.sayHello();
    }
}
