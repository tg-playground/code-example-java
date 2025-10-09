package com.taogen.springiocbyannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainV2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.taogen.springiocbyannotation");
        MyBean myBean = (MyBean) ctx.getBean("MyBean");
        myBean.sayHello();
    }
}
