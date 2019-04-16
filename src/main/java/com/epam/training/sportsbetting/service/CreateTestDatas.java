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

    public CreateTestDatas() {
        sportEvents = generateSportEvents();
    }


    private List<SportEvent> generateSportEvents() {
        List<SportEvent> sportEvents = new ArrayList<>();

        FootballSportEvent footballSportEvent = new FootballSportEvent();
        footballSportEvent.setTitle("Arsenal vs Chelsea");
        footballSportEvent.setStartDate(LocalDateTime.of(2020, 1, 1, 12, 0, 0));
        footballSportEvent.setEndDate(footballSportEvent.getStartDate().plusHours(2));
        footballSportEvent.setBets(generateBets(footballSportEvent));

        sportEvents.add(footballSportEvent);
        return sportEvents;
    }

    private List<Bet> generateBets(SportEvent sportEvent) {
        List<Bet> bets = new ArrayList<>();
        List<String> possibleOutcomes = new ArrayList<>();

        Bet bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("player Oliver Giroud score");
        possibleOutcomes.add("0");
        possibleOutcomes.add("1");
        possibleOutcomes.add("2");
        possibleOutcomes.add("3");
        possibleOutcomes.add("3+");
        bet.setOutcomes(generateOutcomeList(bet, possibleOutcomes));
        possibleOutcomes.clear();
        bet.setType(BetType.PLAYERS_SCORE);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("number of scored goals");
        possibleOutcomes.add("0");
        possibleOutcomes.add("1-2");
        possibleOutcomes.add("3-4");
        possibleOutcomes.add("4+");
        bet.setOutcomes(generateOutcomeList(bet, possibleOutcomes));
        possibleOutcomes.clear();
        bet.setType(BetType.GOALS);
        bets.add(bet);
        bet = null;

        bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("winner");
        possibleOutcomes.add("Arsenal");
        possibleOutcomes.add("Chelsea");
        bet.setOutcomes(generateOutcomeList(bet, possibleOutcomes));
        possibleOutcomes.clear();
        bet.setType(BetType.WINNER);
        bets.add(bet);

        return bets;
    }

    private List<Outcome> generateOutcomeList(Bet bet, List<String> possibleOutcomes) {
        List<Outcome> outcomes = new ArrayList<>();

        possibleOutcomes.forEach(value -> {
            Outcome outcome = new Outcome();
            outcome.setBet(bet);
            outcome.setDescription(value);
            outcome.setOutcomeOdds(generateOutcomeOdd(outcome));
            outcomes.add(outcome);
        });

        return outcomes;
    }

    private List<OutcomeOdd> generateOutcomeOdd(Outcome outcome) {
        List<OutcomeOdd> outcomeOddsList = new ArrayList<>();
        OutcomeOdd outcomeOdd = new OutcomeOdd();
        int random = (int) (Math.random() * 10) + 1;
        outcomeOdd.setOutcome(outcome);
        outcomeOdd.setValue(new BigDecimal(random));
        outcomeOdd.setValidFrom(LocalDateTime.of(2020, 1, 1, 10, 0 ,0));
        outcomeOdd.setValidUntil(outcomeOdd.getValidFrom().plusHours(2));
        outcomeOddsList.add(outcomeOdd);
        return outcomeOddsList;
    }

}
