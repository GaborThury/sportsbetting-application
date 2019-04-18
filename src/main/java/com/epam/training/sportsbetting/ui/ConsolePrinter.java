package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.util.List;

public class ConsolePrinter {
    void printAskName() {
        System.out.println("What is your name?");
    }

    void printAskMoneyQuantity() {
        System.out.println("How much money do you have (more than 0)?");
    }

    void printAskCurrency() {
        System.out.println("What is your currency? (HUF / EUR / USD)");
    }

    void printWelcomeMessage(String name) {
        System.out.format("Welcome %s! \n", name);
    }

    void printBalance(BigDecimal balance, Currency currency) {
        System.out.println("Your balance is: " + balance + " " + currency);
    }

    void printOutcomeOdds(List<SportEvent> sportEvents) {
        if (sportEvents == null) {
            return;
        }
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");


        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        System.out.print(outcomeOdd.getId() + ". " + sportEvent.getTitle());
                        System.out.print(" (start: " + sportEvent.getStartDate() + ")");
                        System.out.print(" Bet: " + bet.getDescription());
                        System.out.print(" Outcome: " + outcome.getDescription());
                        System.out.println(" Actual odd: " + outcomeOdd.getValue());
                    }
                }
            }
        }
    }

    void printWagerSaved(Wager wager) {
        System.out.println(basicWagerInfoToString(wager) + " saved!");
    }

    void printNotEnoughBalance(BigDecimal balance) {
        System.out.println("You don't have enough money, your balance is " + balance);
    }

    void printResults(Player player, List<Wager> wagers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Results: \n");
        wagers.forEach(wager -> {
                    sb.append(basicWagerInfoToString(wager));
                    sb.append(", win: ");
                    sb.append(wager.isWin());
                    sb.append("\n");
                });
        System.out.println(sb.toString());
        System.out.println("Your new balance is: " + player.getBalance());
    }

    private String basicWagerInfoToString(Wager wager) {
        StringBuilder sb = new StringBuilder();

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
        sb.append("]");

        return sb.toString();
    }

}
