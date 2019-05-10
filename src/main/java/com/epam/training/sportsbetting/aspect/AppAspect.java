package com.epam.training.sportsbetting.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AppAspect {

    private long startTime;

    @Autowired
    private Logger logger;

    @Before("within(com.epam.training.sportsbetting.service..*)")
    public void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        logger.info("Calling method: " + joinPoint.getSignature().getName());
        logger.info(" with parameters: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "within(com.epam.training.sportsbetting.service..*)",
        returning = "returnValue")
    public void after(Object returnValue) {
        logger.info("The return value of the method is: " + returnValue);
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("Method took " + executionTime + " ms to execute.");
    }
}
