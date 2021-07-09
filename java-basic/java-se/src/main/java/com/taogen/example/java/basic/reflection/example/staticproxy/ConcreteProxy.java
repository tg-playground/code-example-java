package com.taogen.example.java.basic.reflection.example.staticproxy;

/**
 * @author Taogen
 */
public class ConcreteProxy implements Proxy{

    private RealSubject realSubject = new RealSubject();

    @Override
    public void request() {
        System.out.println("before ...");
        realSubject.request();
        System.out.println("after ...");
    }
}
