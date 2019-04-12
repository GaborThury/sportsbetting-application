package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.service.Service;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.ui.ConsolePrinter;
import com.epam.training.sportsbetting.ui.ConsoleReader;
import com.epam.training.sportsbetting.ui.IO;
import com.epam.training.sportsbetting.ui.ReaderAndPrinter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {

    private IO io;
    private SportBettingService sportBettingService;
    private Player player;
    private List<Wager> wagers = new ArrayList<>();

    public App(SportBettingService sportBettingService, IO io) {
        this.io = io;
        this.sportBettingService = sportBettingService;
    }

    public static void main(String[] args) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        ConsoleReader consoleReader = new ConsoleReader();
        ReaderAndPrinter readerAndPrinter = new ReaderAndPrinter(consolePrinter, consoleReader);
        Service service = new Service();

        App app = new App(service, readerAndPrinter);

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
        while (true) {
            io.printBalance(player);
            io.printOutcomeOdds(sportBettingService.findAllSportEvents());
            if (handleUserBet() == 0) return;
        }

    }

    private void calculateResults() {

    }

    private void printResults() {
        io.printResults(player, wagers);
    }

    private int handleUserBet() {
        ConsoleReader consoleReader = new ConsoleReader();

        //TODO find bet by number
        while (true) {
            int userBetID = consoleReader.readUserBetNumber();
            if (userBetID == 0) return 0;
            BigDecimal wagerAmount;

            while (true) {
                wagerAmount = io.readWagerAmount();
                BigDecimal currentPlayerBalance = player.getBalance();
                if (currentPlayerBalance.compareTo(wagerAmount) > -1) {
                    player.setBalance(currentPlayerBalance.subtract(wagerAmount));
                    break;
                }
                io.printNotEnoughBalance(player);
            }


            Wager wager = new Wager();
            wager.setPlayer(player);
            wager.setAmount(wagerAmount);
            wager.setCurrency(player.getCurrency());
            wager.setOutcomeOdd(null);
            wager.setTimestampCreated(LocalDateTime.now());
            wagers.add(wager);
            io.printWagerSaved(wager);
        }
    }
}
