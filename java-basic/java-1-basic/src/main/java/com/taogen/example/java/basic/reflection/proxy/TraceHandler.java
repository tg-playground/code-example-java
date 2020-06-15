package com.taogen.example.java.basic.reflection.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Taogen
 */
public class TraceHandler implements InvocationHandler {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Proxy object fields for Account interface
     */
    private Integer balance = 0;

    /**
     * Proxy object fields for User interface
     */
    private UserImpl userImpl = new UserImpl();
    private String name;

    public TraceHandler() {
    }

    public TraceHandler(Integer initialValue) {
        this.balance = initialValue;
    }


    /**
     * You can implementing lots of methods from many interface. implemented different methods according by the method and argument parameters.
     * @param proxy proxy object "this" reference, for chain call e.g proxy.method1(x).method1(y).
     * @param method the method that you want to call on the proxy object.
     * @param args args pass to the called method
     * @return You can return the value of a field of the proxy object, or return this reference, or return other calculate value.
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before proxy method calling...");
        printInfoOfMethodAndArgs(method, args);
        Class<?>[] interfaces = proxy.getClass().getInterfaces();
        for (Class cls : interfaces){
            if (cls.equals(Account.class)){
                logger.info("proxy object implements Account interface");
                Object accountResult = handleMethodsInAccountInterface(proxy, method, args);
                if (accountResult != null){
                    return accountResult;
                }
            }
            if (cls.equals(User.class)){
                logger.info("proxy object implements User interface");
                Object userResult = handleMethodsInUserInterfaceByImpl(proxy, method, args);
                if (userResult != null){
                    return userResult;
                }
            }
        }
        logger.info("After proxy method called.");
        // invoke method on proxy object field
        return method.invoke(balance, args);
    }

    private Object handleMethodsInUserInterfaceByFields(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        if ("setName".equals(methodName)) {
            this.name = (String) args[0];
            return proxy;
        }
        if ("getName".equals(methodName)){
            return name;
        }
        return null;

    }

    private Object handleMethodsInUserInterfaceByImpl(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        if ("setName".equals(methodName)) {
            userImpl.setName((String) args[0]);
            return proxy;
        }
        if ("getName".equals(methodName)){
            return userImpl.getName();
        }
        return null;

    }

    private Object handleMethodsInAccountInterface(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        if ("deposit".equals(methodName)) {
            this.balance += (Integer) args[0];
            return proxy;
        }
        if ("get".equals(methodName)){
            return balance;
        }
        return null;
    }

    private void printInfoOfMethodAndArgs(Method method, Object[] args) {
        logger.info("called method: {}", method.getName());
        if (args != null) {
            logger.info("args of {} are: {}", method.getName(), Arrays.asList(args).toString());
        }
    }
}
