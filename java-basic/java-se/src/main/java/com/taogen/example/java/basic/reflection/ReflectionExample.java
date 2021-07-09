package com.taogen.example.java.basic.reflection;

/**
 * @author Taogen
 */
public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Target.class;
        System.out.println(clazz.getName());
        Class clazz2 = new Target().getClass();
        System.out.println(clazz2.getName());
        Class clazz3 = Class.forName("com.taogen.example.java.basic.reflection.Target");
        System.out.println(clazz3.getName());
        clazz.cast(clazz2);
    }
}
