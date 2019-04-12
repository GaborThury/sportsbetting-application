package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateTestDatas {

    public List<SportEvent> generateSportEvents() {
        List<SportEvent> sportEvents = new ArrayList<>();

        FootballSportEvent footballSportEvent = new FootballSportEvent();
        footballSportEvent.setTitle("Arsenal vs Chelsea");
        footballSportEvent.setStartDate(LocalDateTime.of(2020, 1, 1, 12, 0, 0));
        footballSportEvent.setEndDate(footballSportEvent.getStartDate().plusHours(2));
        footballSportEvent.setBets(generateBets());

        sportEvents.add(footballSportEvent);
        return sportEvents;
    }

    private List<Bet> generateBets() {
        List<Bet> bets = new ArrayList<>();

        Bet bet = new Bet();
        bet.setDescription("player Oliver Giroud score");
        bet.setOutcomes(generateOutcomeList("1"));
        bet.setType(BetType.PLAYERS_SCORE);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setDescription("number of scored goals");
        bet.setOutcomes(generateOutcomeList("3"));
        bet.setType(BetType.GOALS);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setDescription("winner");
        bet.setOutcomes(generateOutcomeList("Arsenal"));
        bet.setType(BetType.WINNER);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setDescription("winner");
        bet.setOutcomes(generateOutcomeList("Chelsea"));
        bet.setType(BetType.WINNER);
        bets.add(bet);

        return bets;
    }

    private List<Outcome> generateOutcomeList(String value) {
        List<Outcome> outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.setDescription(value);
        outcome.setOutcomeOdd(generateOutcomeOdd());
        outcomes.add(outcome);
        return outcomes;
    }

    private OutcomeOdd generateOutcomeOdd() {
        OutcomeOdd outcomeOdd = new OutcomeOdd();
        outcomeOdd.setValue(BigDecimal.valueOf(2));
        outcomeOdd.setValidFrom(LocalDateTime.of(2020, 1, 1, 10, 0 ,0));
        outcomeOdd.setValidUntil(outcomeOdd.getValidFrom().plusHours(2));
        return outcomeOdd;
    }

}
