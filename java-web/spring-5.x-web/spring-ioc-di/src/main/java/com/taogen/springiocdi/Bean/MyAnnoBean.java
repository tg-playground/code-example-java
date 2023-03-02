package com.taogen.springiocdi.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyAnnoBean implements AbstractBean
{
    @Value("AnnoBean1")
    private String name;
    @Autowired
    private MyInjectBean injectBean;
    @Override
    public String sayHello() {
        String s = "Hello by " + this.name;
        System.out.println(s);
        injectBean.sayHello();
        return s;
    }
}
