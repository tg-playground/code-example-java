package com.taogen.example.wechat.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
@Aspect
public class WxControllerAspect {
    private Logger logger = LogManager.getLogger();

    @Pointcut("execution(* com.taogen.example.wechat.controller.*.*(..))")
    public void wxControllerPointCut() {
        // define pointcut not need method body
    }

    @Around("wxControllerPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String classAndMethodName = pjp.getSignature().toShortString();
        logger.debug("<<<");
        logger.debug("{} is beginning...", classAndMethodName);
        long start = System.currentTimeMillis();
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        String methodName = pjp.getSignature().getName();
        logger.debug("{}() return: {}", methodName, output);
        logger.debug("{} execution time: {} ms.", classAndMethodName, elapsedTime);
        logger.debug(">>>");
        return output;
    }
}
