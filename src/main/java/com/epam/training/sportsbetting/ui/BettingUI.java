package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BettingUI implements IO {

    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;
    private Player player;

    public BettingUI(ConsolePrinter consolePrinter, ConsoleReader consoleReader, Player player) {
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
        this.player = player;
    }

    @Override
    public Player readPlayerData() {
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
        consolePrinter.printWelcomeMessage(player.getName());
    }

    @Override
    public void printBalance(Player player) {
        consolePrinter.printBalance(player.getBalance(), player.getCurrency());
    }

    @Override
    public void printOutcomeOdds(List<SportEvent> sportEvents) {
        consolePrinter.printOutcomeOdds(sportEvents);
    }

    @Override
    public BigDecimal readWagerAmount() {
        return consoleReader.readWagerAmount();
    }

    @Override
    public void printWagerSaved(Wager wager) {
        consolePrinter.printWagerSaved(wager);
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        consolePrinter.printNotEnoughBalance(player.getBalance());
    }

    @Override
    public void printResults(Player player, List<Wager> wagers) {
        consolePrinter.printResults(player, wagers);
    }
}
