package com.taogen.springExplore;

import com.taogen.springExplore.bean.Image;
import com.taogen.springExplore.bean.RealImage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Main
{


    public static void main(String[] args)
    {
//        System.out.println("hello ");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Image image = (Image) context.getBean("realImage");
        image.display();
        RealImage image2 = new RealImage("hello");

    }

    /**
     *
     * @param image iamge name
     */
    static void test(Image image)
    {
        image.display();
    }

    static void test(String imageName)
    {

    }
}
