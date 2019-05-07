package com.epam.training.sportsbetting.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import java.util.Scanner;


@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "com.epam.training.sportsbetting")
public class AppConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
