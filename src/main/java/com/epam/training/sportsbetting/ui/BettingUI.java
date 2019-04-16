package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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
            for (Bet bet : sportEvent.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        System.out.print(betCounter + ". " + sportEvent.getTitle());
                        System.out.print(" (start: " + sportEvent.getStartDate() + ")");
                        System.out.print(" Bet: " + bet.getDescription());
                        System.out.print(" Outcome: " + outcome.getDescription());
                        System.out.println(" Actual odd: " + outcomeOdd.getValue());
                        betCounter++;
                    }
                }
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
        StringBuilder sb = new StringBuilder();

        sb.append("Results: \n");
        for (Wager wager : wagers) {
            sb.append("Wager '");
            sb.append(wager.getOutcomeOdd().getOutcome().getBet().getDescription());
            sb.append("=");
            sb.append(wager.getOutcomeOdd().getOutcome().getDescription());
            sb.append("' of ");
            sb.append(wager.getOutcomeOdd().getOutcome().getBet().getSportEvent().getTitle());
            sb.append(" [odd: " );
            sb.append(wager.getOutcomeOdd().getValue());
            sb.append(", amount: ");
            sb.append(wager.getAmount());
            sb.append("], win: ");
            sb.append(wager.isWin());
            sb.append("\n");
        }
        System.out.println(sb.toString());
        System.out.println("Your new balance is: " + player.getBalance());
    }
}
