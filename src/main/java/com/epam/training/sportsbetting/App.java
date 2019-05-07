package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.Configuration.AppConfiguration;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.UserBetService;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import com.epam.training.sportsbetting.ui.IO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class App {

    private IO io;
    private List<SportEvent> sportEvents;
    private Player player;
    private List<Wager> userWagers = new ArrayList<>();
    private UserBetService userBetService;
    private ConsoleReader consoleReader;


    public App(SportBettingService sportBettingService, IO io, UserBetService userBetService, ConsoleReader consoleReader) {
        this.io = io;
        this.userBetService = userBetService;
        this.consoleReader = consoleReader;
        sportEvents = sportBettingService.findAllSportEvents();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        App app = context.getBean(App.class);
        app.createPlayer();
        app.play();
        app.calculateResults();
        app.printResults();
    }

    private void createPlayer() {
       player = io.readPlayerData();
    }

    public void play() {
        io.printWelcomeMessage(player);

        while (playerHasMoney()) {
            io.printBalance(player);
            io.printOutcomeOdds(sportEvents);

            int userChosenOutcomeId = readUserChosenOutcome();
            if (userChosenOutcomeId == 0) return;
            OutcomeOdd userChosenOutcomeOdd = findOutcomeOddById(userChosenOutcomeId);

            while (true) {
                BigDecimal wagerAmount = io.readWagerAmount();
                BigDecimal playerBalance = player.getBalance();
                if (playerHasEnoughMoneyForThisWager(wagerAmount)) {
                    player.setBalance(playerBalance.subtract(wagerAmount));
                    Wager wager = userBetService.createWager(player, wagerAmount, userChosenOutcomeOdd);

                    userWagers.add(wager);
                    io.printWagerSaved(wager);
                    break;
                }
                io.printNotEnoughBalance(player);
            }
        }
    }

    private int readUserChosenOutcome() {
        return consoleReader.readUserBetNumber();
    }

    private OutcomeOdd findOutcomeOddById(int numberOfTheUserChosenOutcome) {
        return userBetService.findOutcomeOddById(
                numberOfTheUserChosenOutcome,
                sportEvents);
    }


    private void calculateResults() {
        Result result = userBetService.generateResult(sportEvents);
        userBetService.summarizeResults(result, userWagers, player);
    }

    private void printResults() {
        io.printResults(player, userWagers);
    }

    private boolean playerHasMoney() {
        return player.getBalance().compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean playerHasEnoughMoneyForThisWager(BigDecimal wagerAmount) {
        return player.getBalance().compareTo(wagerAmount) > -1;
    }
}
