package com.taogen.example.java.basic.reflection.example.staticproxy;

/**
 * @author Taogen
 */
public class Client {
    public static void main(String[] args){
        Subject subject = new ConcreteProxy();
        subject.request();
    }
}
