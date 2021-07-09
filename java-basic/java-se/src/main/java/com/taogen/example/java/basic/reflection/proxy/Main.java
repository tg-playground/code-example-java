package com.taogen.example.java.basic.reflection.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Taogen
 */
public class Main {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
//        testAccount();
        testUser();
    }

    public static void testAccount(){
        Account proxy = (Account) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Account.class}, new TraceHandler());
        proxy.deposit(1).deposit(11);
        LOGGER.info("Account result: {}", proxy.get());
    }

    public static void testUser(){
        User proxy = (User) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{User.class}, new TraceHandler());
        proxy.setName("Foo");
        LOGGER.info("User result: {}", proxy.getName());
    }

    public static void test1(){
        Object[] elements = new Object[100];
        for (int i = 0; i < elements.length; i++){
            Integer value = i + 1;
            elements[i] = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, new TraceHandler(value));
        }

        Integer random = new Random().nextInt(elements.length) + 1;
        LOGGER.info("Random is {}", random);
        int result = Arrays.binarySearch(elements, random);
        if (result > 0){
            LOGGER.info("search result is : {}", elements[result]);
        }

    }

    public static void test(){

        Object value = new Object();
        TraceHandler handler = new TraceHandler();
        Class[] interfaces = new Class[]{Comparable.class};
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interfaces, handler);
        Comparable comparable = (Comparable) proxy;
        LOGGER.info("proxy ", comparable.compareTo(1));
    }
}
