package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.service.SportBettingService;
import com.epam.training.sportsbetting.service.UserInputReader;
import com.epam.training.sportsbetting.ui.IO;
import com.epam.training.sportsbetting.ui.Printer;

import java.math.BigDecimal;

public class App {

    IO io;
    SportBettingService sportBettingService;

    public App(SportBettingService sportBettingService, IO io) {
        this.io = io;
        this.sportBettingService = sportBettingService;
    }

    public static void main(String[] args) {
        App app = new App(null, null);
        app.createPlayer();
        app.play();
    }

    public void play(){
        Printer printer = new Printer();
        UserInputReader userInputReader = new UserInputReader();

        printer.printAskName();
        userInputReader.readPlayerName();

        printer.printAskMoneyQuantity();
        userInputReader.readPlayerMoneyQuantity();

        printer.printAskCurrency();
        userInputReader.readPlayerCurrency();
    }

    private void createPlayer() {
        Player player = new Player();
        player.setName("Rezső");
        player.setBalance(BigDecimal.valueOf(1000));
        player.setCurrency(Currency.HUF);
    }

    private void calculateResults() {

    }

    private void printResults() {

    }
}
