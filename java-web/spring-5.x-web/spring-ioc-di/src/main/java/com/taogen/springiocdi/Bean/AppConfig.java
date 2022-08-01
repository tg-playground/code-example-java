package com.taogen.springiocdi.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public MyJavaBean getMyJavaBean()
    {
        MyJavaBean javaBean = new MyJavaBean("javaBean1");
        javaBean.setInjectBean(new MyInjectBean("injectBeanByJavaBean"));
        return javaBean;
    }
}
