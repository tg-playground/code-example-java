package com.taogen.example.springframework.ioc.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Taogen
 */
public class IocWithAnnotationExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyAnnotationBean.class);
        ctx.refresh();
        MyAnnotationBean myAnnotationBean = (MyAnnotationBean) ctx.getBean(MyAnnotationBean.class);
        myAnnotationBean.sayHello();
        ApplicationContext applicationContext;
    }
}
