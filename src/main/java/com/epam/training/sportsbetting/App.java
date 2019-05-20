package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.configuration.AppConfiguration;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.service.UserBetService;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import com.epam.training.sportsbetting.ui.IO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class App {

    private IO io;
    private Player player;
    private UserBetService userBetService;
    private ConsoleReader consoleReader;
    private SportBettingService sportBettingService;

    public App(IO io, UserBetService userBetService, ConsoleReader consoleReader, SportBettingService sportBettingService) {
        this.io = io;
        this.userBetService = userBetService;
        this.consoleReader = consoleReader;
        this.sportBettingService = sportBettingService;
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
        player = (io.readPlayerData());
        sportBettingService.savePlayer(player);
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

    private OutcomeOdd findOutcomeOddById(int idToGet) {
        return userBetService.findOutcomeOddById(idToGet);
    }


    private void calculateResults() {
        Result result = userBetService.generateResult();
        userBetService.summarizeResults(result, player);
    }

    private void printResults() {
        io.printResults(player, sportBettingService.findAllWagers());
    }

    private boolean playerHasMoney() {
        return player.getBalance().compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean playerHasEnoughMoneyForThisWager(BigDecimal wagerAmount) {
        return player.getBalance().compareTo(wagerAmount) > -1;
    }
}
