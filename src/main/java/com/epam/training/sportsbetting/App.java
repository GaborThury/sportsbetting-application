package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.Configuration.Configuration;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.UserBetService;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import com.epam.training.sportsbetting.ui.IO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {

    private IO io;
    private SportBettingService sportBettingService;
    private Player player;
    private List<Wager> userWagers = new ArrayList<>();
    private UserBetService userBetService;
    private ConsoleReader consoleReader;

    public App(SportBettingService sportBettingService, IO io, UserBetService userBetService, ConsoleReader consoleReader) {
        this.io = io;
        this.sportBettingService = sportBettingService;
        this.userBetService = userBetService;
        this.consoleReader = consoleReader;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
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
            io.printOutcomeOdds(sportBettingService.findAllSportEvents());

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
                sportBettingService.findAllSportEvents());
    }


    private void calculateResults() {
        Result result = userBetService.generateResult(sportBettingService.findAllSportEvents());
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
