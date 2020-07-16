package com.taogen.springiocdi;

import com.taogen.springiocdi.Bean.MyAnnoBean;
import com.taogen.springiocdi.Bean.MyJavaBean;
import com.taogen.springiocdi.Bean.MyXmlBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyXmlBean xmlBean = applicationContext.getBean(MyXmlBean.class);
        xmlBean.sayHello();
        MyAnnoBean annoBean = applicationContext.getBean(MyAnnoBean.class);
        annoBean.sayHello();
        MyJavaBean javaBean = applicationContext.getBean(MyJavaBean.class);
        javaBean.sayHello();
    }
}
