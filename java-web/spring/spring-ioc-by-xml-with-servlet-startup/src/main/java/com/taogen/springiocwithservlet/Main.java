package com.taogen.springiocwithservlet;

import com.taogen.springiocwithservlet.bean.MyBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main (String[] args)
    {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean myBean = (MyBean) beanFactory.getBean("MyBean");
        myBean.sayHello();
    }
}
