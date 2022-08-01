package com.taogen.springaopbyxml.aspect;

public class Logging
{
    public void beforeAdvice()
    {
        System.out.println("before..");
    }

    public void afterAdvice()
    {
        System.out.println("after..");
    }

    public void afterReturnAdvice()
    {
        System.out.println("after return..");
    }

    public void afterThrowingAdvice()
    {
        System.out.println("after throwing..");
    }
}
