package com.taogen.springaopbyxml;

import com.taogen.springaopbyxml.bean.MyBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main
{
    public static void main(String[] args)
    {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean myBean = beanFactory.getBean(MyBean.class);
        myBean.sayHello();
    }
}
