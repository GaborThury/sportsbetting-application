package com.epam.training.sportsbetting.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AppAspect {

    private long startTime;

    @Before("within(com.epam.training.sportsbetting.service..*)")
    public void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        System.out.print("Calling method: " + joinPoint.getSignature().getName());
        System.out.println(" with parameters: " + Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning(pointcut = "within(com.epam.training.sportsbetting.service..*)",
        returning = "returnValue")
    public void after(Object returnValue) {
        System.out.println("The return value of the method is: " + returnValue);
        long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("Method took " + executionTime + " ms to execute.");
    }
}
