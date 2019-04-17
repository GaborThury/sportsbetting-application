package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserBetService {

    public OutcomeOdd findOutcomeOddByNumber(int userBet, List<SportEvent> sportEvents) {
        int betCounter = 1;

        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        if (userBet == betCounter) {
                            return outcomeOdd;
                        }
                        betCounter++;
                    }
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

    public Result generateResult(List<SportEvent> sportEvents) {
        Result result = new Result();
        List<Outcome> winnerOutcomes = new ArrayList<>();

        for (SportEvent sportEvent : sportEvents) {
            for (Bet bet : sportEvent.getBets()) {
                int randomInt = getRandomNumberInRange(0, bet.getOutcomes().size() - 1);
                winnerOutcomes.add(bet.getOutcomes().get(randomInt));
            }
        }
        result.setWinnerOutcomes(winnerOutcomes);
        return result;
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void setWinnerWagersTrue(Result result, List<Wager> userWagers, Player player) {
        List<Outcome> winnerOutcomes = result.getWinnerOutcomes();

        for (Outcome winnerOutcome : winnerOutcomes) {
            for (Wager userWager : userWagers) {
                userWager.getOutcomeOdd().getOutcome().getBet().getSportEvent().setResult(result);
                if (winnerOutcome.getBet().getDescription().equals(userWager.getOutcomeOdd().getOutcome().getBet().getDescription())
                        && winnerOutcome.getDescription().equals(userWager.getOutcomeOdd().getOutcome().getDescription())) {
                    userWager.setWin(true);
                    player.setBalance(player.getBalance()
                            .add(userWager.getAmount().multiply(userWager.getOutcomeOdd().getValue())));
                }
            }
        }
    }
}
