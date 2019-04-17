package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.Currency;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner = new Scanner(System.in);

    String readPlayerName() {
        return scanner.nextLine();
    }

    BigDecimal readPlayerBalance() {
        return new BigDecimal(scanner.nextLine());
    }

    Currency readPlayerCurrency() {

        while (true) {
            String s = scanner.nextLine().toUpperCase();
            switch (s) {
                case "EUR":
                    return Currency.EUR;
                case "USD":
                    return Currency.USD;
                case "HUF":
                    return Currency.HUF;
            }
            System.out.println("You entered invalid currency! Please enter HUF, EUR or USD");
        }
    }

    BigDecimal readWagerAmount() {
        System.out.println("What amount do you wish to bet on it?");
        return scanner.nextBigDecimal();
    }

    public int readUserBetNumber() {
        String input = scanner.nextLine();
        return "q".equals(input) ? 0 : Integer.parseInt(input);
    }

}
