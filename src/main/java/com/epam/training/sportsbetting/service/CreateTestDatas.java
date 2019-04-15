package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateTestDatas {

    private static CreateTestDatas instance = null;
    List<SportEvent> sportEvents;
    private CreateTestDatas() {
        generateSportEvents();
    }

    public static CreateTestDatas getInstance() {
        if (instance == null) {
            instance = new CreateTestDatas();
        }
        return instance;
    }


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
        List<String> values = new ArrayList<>();

        Bet bet = new Bet();
        bet.setDescription("player Oliver Giroud score");
        values.add("0");
        values.add("1");
        values.add("2");
        values.add("3");
        values.add("3+");
        bet.setOutcomes(generateOutcomeList(values));
        values.clear();
        bet.setType(BetType.PLAYERS_SCORE);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setDescription("number of scored goals");
        values.add("0");
        values.add("1-2");
        values.add("3-4");
        values.add("4+");
        bet.setOutcomes(generateOutcomeList(values));
        values.clear();
        bet.setType(BetType.GOALS);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setDescription("winner");
        values.add("Arsenal");
        values.add("Chelsea");
        bet.setOutcomes(generateOutcomeList(values));
        values.clear();
        bet.setType(BetType.WINNER);
        bets.add(bet);

        return bets;
    }

    private List<Outcome> generateOutcomeList(List<String> values) {
        List<Outcome> outcomes = new ArrayList<>();

        values.forEach(value -> {
            Outcome outcome = new Outcome();
            outcome.setDescription(value);
            outcome.setOutcomeOdd(generateOutcomeOdd());
            outcomes.add(outcome);
        });

        return outcomes;
    }

    private OutcomeOdd generateOutcomeOdd() {
        OutcomeOdd outcomeOdd = new OutcomeOdd();
        int random = (int) (Math.random() * 10) + 1;
        outcomeOdd.setValue(new BigDecimal(random));
        outcomeOdd.setValidFrom(LocalDateTime.of(2020, 1, 1, 10, 0 ,0));
        outcomeOdd.setValidUntil(outcomeOdd.getValidFrom().plusHours(2));
        return outcomeOdd;
    }

}
