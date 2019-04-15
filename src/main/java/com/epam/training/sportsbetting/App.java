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

        while (player.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            io.printBalance(player);
            io.printOutcomeOdds(sportBettingService.findAllSportEvents());

            int userBetNumber = consoleReader.readUserBetNumber();
            if (userBetNumber == 0) return;

            UserBetService userBetService = new UserBetService();
            OutcomeOdd userOutcomeOdd = userBetService.findOutcomeOddByNumber(userBetNumber);
            BigDecimal wagerAmount;
            BigDecimal playerBalance;

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
        UserBetService userBetService = new UserBetService();
        BettingService bettingService = new BettingService();
        Result result;

        result = userBetService.generateResult(bettingService.findAllSportEvents());
        result.getWinnerOutcomes().forEach(System.out::println);

        bettingService.findAllSportEvents().get(0).setResult(result);



        //TODO: compare the results with the user's wagers
        userBetService.generateRandomWagerResults(userWagers, player);
    }

    private void printResults() {
        io.printResults(player, userWagers);
    }

}
