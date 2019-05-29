package com.epam.training.sportsbetting.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;


@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.epam.training.sportsbetting.repository")
@ComponentScan(basePackages = "com.epam.training.sportsbetting")
public class AppConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Logger logger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

}
