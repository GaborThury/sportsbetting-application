package com.epam.training.sportsbetting.Configuration;

import com.epam.training.sportsbetting.App;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.service.BettingService;
import com.epam.training.sportsbetting.service.UserBetService;
import com.epam.training.sportsbetting.ui.BettingUI;
import com.epam.training.sportsbetting.ui.ConsolePrinter;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Scanner;


@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ConsolePrinter consolePrinter() {
        return new ConsolePrinter();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ConsoleReader consoleReader() {
        return new ConsoleReader(scanner());
    }

    @Bean
    public BettingUI bettingUI() {
        return new BettingUI(consolePrinter(), consoleReader(), player());
    }

    @Bean
    public BettingService bettingService() {
        return new BettingService();
    }

    @Bean
    public UserBetService userBetService() {
        return new UserBetService();
    }

    @Bean
    public Player player() {
        return new Player();
    }

    @Bean
    @Scope("prototype")
    public Wager wager() {
        return new Wager();
    }

    @Bean
    public App app() {
        return new App(bettingService(), bettingUI(), userBetService(), consoleReader());
    }
}
