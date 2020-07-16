package com.taogen.springiocdi.Bean;

public class MyJavaBean implements AbstractBean
{
    private String name;
    private MyInjectBean injectBean;
    public MyJavaBean(){}
    public MyJavaBean(String name)
    {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyInjectBean getInjectBean() {
        return injectBean;
    }

    public void setInjectBean(MyInjectBean injectBean) {
        this.injectBean = injectBean;
    }

    @Override
    public String sayHello() {
        String s = "Hello by " + this.name;
        System.out.println(s);
        injectBean.sayHello();
        return s;
    }
}
