package com.epam.training.sportsbetting.service;


import java.util.Scanner;

public class UserInputReader {

    private Scanner scanner = new Scanner(System.in);

    public String readPlayerName() {
        return scanner.nextLine();
    }

    public String readPlayerMoneyQuantity() {
        return scanner.nextLine();
    }

    public String readPlayerCurrency() {
        return scanner.nextLine();
    }
}
