package com.taogen.springiocwithservlet.servlet;

import com.taogen.springiocwithservlet.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet
{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("myservlet before11..");

        // Using ioc bean in servlet. By utility class get bean.
        ApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        //WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        MyBean myBean = (MyBean) webApplicationContext.getBean("MyBean1");
        myBean.sayHello();

        try {
            PrintWriter pw = response.getWriter();
            pw.write("Hello by MyServlet --");
            pw.write("\n" + myBean.sayHello());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
