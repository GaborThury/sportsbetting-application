package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class UserBetService {

    public Bet findBetByNumber(int userBet) {
        BettingService bettingService = new BettingService();
        List<SportEvent> sportEvents = bettingService.findAllSportEvents();
        int betCounter = 1;

        for (SportEvent sportEvent : sportEvents) {
            for (int j = 0; j < sportEvent.getBets().size(); j++) {
                if (userBet == betCounter) {
                    return sportEvent.getBets().get(j);
                }
                betCounter++;
            }
        }
        return null;
    }

    public Wager createWager(Player player, BigDecimal wagerAmount, Bet userBet) {
        Wager wager = new Wager();
        wager.setPlayer(player);
        wager.setAmount(wagerAmount);
        wager.setCurrency(player.getCurrency());
        wager.setOutcomeOdd(userBet.getOutcomes().get(0).getOutcomeOdd());
        wager.setTimestampCreated(LocalDateTime.now());
        return wager;
    }

}
