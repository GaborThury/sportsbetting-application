package com.epam.training.sportsbetting.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Scanner;


@org.springframework.context.annotation.Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.epam.training.sportsbetting")
public class AppConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
