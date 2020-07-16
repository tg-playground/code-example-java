package com.taogen.springiocwithservlet;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.taogen.springiocwithservlet.bean.MyBean;

public class Main
{
    public static void main (String[] args)
    {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(MyBean.class);
//        ctx.refresh();
//        MyBean myBean = (MyBean) ctx.getBean(MyBean.class);
//        myBean.sayHello();

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean myBean1 = (MyBean) beanFactory.getBean("MyBean1");
        myBean1.sayHello();

        MyBean myBean2 = (MyBean) beanFactory.getBean(MyBean.class);
        myBean2.sayHello();


    }
}
