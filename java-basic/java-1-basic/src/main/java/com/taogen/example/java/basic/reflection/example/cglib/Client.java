package com.taogen.example.java.basic.reflection.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author Taogen
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        MethodInterceptor methodInterceptor = new MyMethodInterceptor(subject);
        Subject proxy = (Subject) Enhancer.create(Subject.class, methodInterceptor);
        proxy.request();
    }
}
