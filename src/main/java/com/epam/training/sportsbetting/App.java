package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.service.UserBetService;
import com.epam.training.sportsbetting.service.BettingService;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.ui.ConsolePrinter;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import com.epam.training.sportsbetting.ui.IO;
import com.epam.training.sportsbetting.ui.BettingUI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {

    private IO io;
    private SportBettingService sportBettingService;
    private Player player;
    private List<Wager> userWagers = new ArrayList<>();
    private UserBetService userBetService = new UserBetService();

    public App(SportBettingService sportBettingService, IO io) {
        this.io = io;
        this.sportBettingService = sportBettingService;
    }

    public static void main(String[] args) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleReader consoleReader = new ConsoleReader();
        BettingUI bettingUI = new BettingUI(consolePrinter, consoleReader);
        BettingService bettingService = new BettingService();

        App app = new App(bettingService, bettingUI);

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
        ConsoleReader consoleReader = new ConsoleReader();

        while (playerHasMoney()) {
            io.printBalance(player);
            io.printOutcomeOdds(sportBettingService.findAllSportEvents());

            int numberOfTheUserChosenOutcome = consoleReader.readUserBetNumber();
            if (numberOfTheUserChosenOutcome == 0) return;
            OutcomeOdd userChosenOutcomeOdd = userBetService.findOutcomeOddByNumber(
                    numberOfTheUserChosenOutcome,
                    sportBettingService.findAllSportEvents());

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
