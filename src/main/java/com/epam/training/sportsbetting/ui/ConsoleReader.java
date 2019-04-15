package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.Currency;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner = new Scanner(System.in);

    public String readPlayerName() {
        return scanner.nextLine();
    }

    public BigDecimal readPlayerBalance() {
        return new BigDecimal(scanner.nextLine());
    }

    public Currency readPlayerCurrency() {

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

    public BigDecimal readWagerAmount() {
        return scanner.nextBigDecimal();
    }

    public int readUserBetNumber() {
        String input = scanner.nextLine();
        return "q".equals(input) ? 0 : Integer.parseInt(input);
    }

}
