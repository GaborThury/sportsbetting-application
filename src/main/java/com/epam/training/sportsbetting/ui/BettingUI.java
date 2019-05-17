package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BettingUI implements IO {

    @Autowired
    private PlayerRepository playerRepository;

    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;

    public BettingUI(ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
    }

    @Override
    public Player readPlayerData() {
        Player player = new Player();
        player.setId(1);

        consolePrinter.printAskName();
        player.setName(consoleReader.readPlayerName());

        consolePrinter.printAskMoneyQuantity();
        player.setBalance(consoleReader.readPlayerBalance());

        consolePrinter.printAskCurrency();
        player.setCurrency(consoleReader.readPlayerCurrency());

        playerRepository.save(player);
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
