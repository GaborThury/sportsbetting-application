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

    @Before("execution(* com.epam.training.sportsbetting.service.UserBetService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.print("Calling method: " + joinPoint.getSignature().getName());
        System.out.println(" with parameters: " + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(pointcut = "execution(* com.epam.training.sportsbetting.service.UserBetService.*(..))",
        returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        System.out.println("The return value of the method is: " + returnValue);
    }
}
