package com.taogen.example.java.basic.reflection.example.jdkdynamic;

import java.lang.reflect.Proxy;

/**
 * @author Taogen
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Subject.class}, new MyInvocationHandler());
        subject.request();
    }
}
