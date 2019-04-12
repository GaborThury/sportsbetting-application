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
        String s = scanner.nextLine();
        Currency currency = Currency.EUR;
        return currency;
    }

    public BigDecimal readWagerAmount() {
        return scanner.nextBigDecimal();
    }

    public int readUserBetNumber() {
        String input = scanner.nextLine();
        return "q".equals(input) ? 0 : Integer.parseInt(input);
    }

}
