package com.taogen.example.java.basic.reflection.example.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Taogen
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private final Subject realSubject;

    public MyMethodInterceptor(Subject subject){
        this.realSubject = subject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before...");
        Object result = method.invoke(realSubject, args);
        System.out.println("after...");
        return result;
    }
}
