package com.taogen.springaopbyanno.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect
{
    @Pointcut("execution(* com.taogen.springaopbyanno.bean.*.*(..))")
    public void loggingMyBean() {}

    @Before("loggingMyBean()")
    public void beforeAdvice()
    {
        System.out.println("Before..");
    }
    @After("loggingMyBean()")
    public void afterAdvice()
    {
        System.out.println("After..");
    }
    @AfterReturning("loggingMyBean()")
    public void afterReturnAdvice()
    {
        System.out.println("After return..");
    }
}
