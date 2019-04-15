package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserBetService {

    public OutcomeOdd findOutcomeOddByNumber(int userBet) {
        BettingService bettingService = new BettingService();
        List<SportEvent> sportEvents = bettingService.findAllSportEvents();
        int betCounter = 1;

        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    if (userBet == betCounter) {
                        return outcome.getOutcomeOdd();
                    }
                    betCounter++;
                }
            }
        }
        return null;
    }

    public Wager createWager(Player player, BigDecimal wagerAmount, OutcomeOdd userOutcomeOdd) {
        Wager wager = new Wager();
        wager.setPlayer(player);
        wager.setAmount(wagerAmount);
        wager.setCurrency(player.getCurrency());
        wager.setOutcomeOdd(userOutcomeOdd);
        wager.setTimestampCreated(LocalDateTime.now());
        return wager;
    }

    public void generateRandomWagerResults(List<Wager> userWagers, Player player) {
        for (Wager userWager : userWagers) {
            if (Math.random() > 0.2) {
                userWager.setWin(true);
                player.setBalance(player.getBalance()
                        .add(userWager.getAmount().multiply(userWager.getOutcomeOdd().getValue())));
            } else {
                userWager.setWin(false);
            }
        }
    }

    public void markWonWagers(List<Wager> userWagers, Player player, Result result) {
        for (Wager userWager : userWagers) {
            if (result.getWinnerOutcomes().contains()) {
                userWager.setWin(true);
            }
        }
    }

    public Result generateResult(List<SportEvent> sportEvents) {
        Result result = new Result();
        List<Outcome> finalList = new ArrayList<>();

        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                int randomInt = getRandomNumberInRange(0, bet.getOutcomes().size() - 1);
                finalList.add(bet.getOutcomes().get(randomInt));
            }
        }

        result.setWinnerOutcomes(finalList);
        return result;
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
