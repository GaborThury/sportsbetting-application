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

    public void play(){
        io.printWelcomeMessage(player);
        ConsoleReader consoleReader = new ConsoleReader();
        int userChosenBetNumber;
        OutcomeOdd userOutcomeOdd;
        BigDecimal wagerAmount;
        BigDecimal playerBalance;

        while (player.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            io.printBalance(player);
            io.printOutcomeOdds(sportBettingService.findAllSportEvents());

            userChosenBetNumber = consoleReader.readUserBetNumber();
            if (userChosenBetNumber == 0) return;
            userOutcomeOdd = userBetService.findOutcomeOddByNumber(userChosenBetNumber, sportBettingService.findAllSportEvents());

            while (true) {
                wagerAmount = io.readWagerAmount();
                playerBalance = player.getBalance();
                if (playerBalance.compareTo(wagerAmount) > -1) {
                    player.setBalance(playerBalance.subtract(wagerAmount));
                    Wager wager = userBetService.createWager(player, wagerAmount, userOutcomeOdd);
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

        result.getWinnerOutcomes().forEach(outcome -> {
            System.out.print(outcome.getBet().getDescription() + ": ");
            System.out.println(outcome.getDescription());
        });

        sportBettingService.findAllSportEvents().get(0).setResult(result);
        userBetService.setWinnerWagersTrue(result, userWagers, player);
    }

    private void printResults() {
        io.printResults(player, userWagers);
    }

}
