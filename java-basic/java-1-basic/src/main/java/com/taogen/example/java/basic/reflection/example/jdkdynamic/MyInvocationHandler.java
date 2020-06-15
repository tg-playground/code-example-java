package com.taogen.example.java.basic.reflection.example.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Taogen
 */
public class MyInvocationHandler implements InvocationHandler {
    private RealSubject realSubject = new RealSubject();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before...");
        Object result = method.invoke(realSubject, args);
        System.out.println("after...");
        return result;
    }
}
