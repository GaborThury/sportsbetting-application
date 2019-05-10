package com.epam.training.sportsbetting.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import java.util.Scanner;


@org.springframework.context.annotation.Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.epam.training.sportsbetting")
public class AppConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

}
