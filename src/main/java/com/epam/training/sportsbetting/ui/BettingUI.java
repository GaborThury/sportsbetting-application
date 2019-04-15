package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class BettingUI implements IO {

    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;

    public BettingUI(ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    @Override
    public Player readPlayerData() {
        Player player = new Player();

        consolePrinter.printAskName();
        player.setName(consoleReader.readPlayerName());

        consolePrinter.printAskMoneyQuantity();
        player.setBalance(consoleReader.readPlayerBalance());

        consolePrinter.printAskCurrency();
        player.setCurrency(consoleReader.readPlayerCurrency());

        return player;
    }

    @Override
    public void printWelcomeMessage(Player player) {
        System.out.format("Welcome %s! \n", player.getName());
    }

    @Override
    public void printBalance(Player player) {
        System.out.println("Your balance is: " + player.getBalance() + " " + player.getCurrency());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> sportEvents) {
        if (sportEvents == null) {
            return;
        }
        int betCounter = 1;
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");


        for (SportEvent sportEvent : sportEvents) {
            int betsListSize = sportEvent.getBets().size();
            for (int j = 0; j < betsListSize; j++) {
                System.out.print(betCounter + ". " + sportEvent.getTitle());
                System.out.print(sportEvent.getBets().get(j));
                System.out.println();
                betCounter++;
            }
        }
    }

    @Override
    public BigDecimal readWagerAmount() {
        System.out.println("What amount do you wish to bet on it?");
        return consoleReader.readWagerAmount();
    }

    @Override
    public void printWagerSaved(Wager wager) {
        System.out.println("Wager " + wager.toString() + "saved!");
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.println("You don't have enough money, your balance is " + player.getBalance());
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        System.out.println("Results:");
        wagers.forEach(System.out::println);
        System.out.println("Your new balance is: " + player.getBalance());
    }
}