package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TestDataCreator {

    private static TestDataCreator instance = null;
    List<SportEvent> sportEvents;

    public TestDataCreator() {
        sportEvents = generateSportEvents();
    }


    private List<SportEvent> generateSportEvents() {
        List<SportEvent> sportEvents = new ArrayList<>();

        FootballSportEvent footballSportEvent = new FootballSportEvent();
        footballSportEvent.setTitle("Arsenal vs Chelsea");
        footballSportEvent.setStartDate(LocalDateTime.of(2020, 1, 1, 12, 0, 0));
        footballSportEvent.setEndDate(footballSportEvent.getStartDate().plusHours(2));
        footballSportEvent.setBets(generateBetsForArsenalVsChelsea(footballSportEvent));

        sportEvents.add(footballSportEvent);

        TennisSportEvent tennisSportEvent = new TennisSportEvent();
        tennisSportEvent.setTitle("Djokovic vs Nadal");
        tennisSportEvent.setStartDate(LocalDateTime.of(2019, 4, 18, 13, 0, 0));
        tennisSportEvent.setEndDate(tennisSportEvent.getStartDate().plusHours(4));
        tennisSportEvent.setBets(generateBetsForDjokovicVsNadal(tennisSportEvent));

        sportEvents.add(tennisSportEvent);

        return sportEvents;
    }

    private List<Bet> generateBetsForDjokovicVsNadal(SportEvent sportEvent) {
        List<Bet> bets = new ArrayList<>();
        List<String> possibleOutcomes = new ArrayList<>();

        Bet bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("winner");
        possibleOutcomes.add("Novak Djokovic");
        possibleOutcomes.add("Rafael Nadal");
        bet.setOutcomes(generateOutcomeList(bet, possibleOutcomes));
        possibleOutcomes.clear();
        bet.setType(BetType.WINNER);
        bets.add(bet);

        bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("Number of sets");
        possibleOutcomes.add("less than 2,5");
        possibleOutcomes.add("more than 2,5");
        bet.setOutcomes(generateOutcomeList(bet, possibleOutcomes));
        possibleOutcomes.clear();
        bet.setType(BetType.NUMBER_OF_SETS);
        bets.add(bet);

        return bets;
    }

    private List<Bet> generateBetsForArsenalVsChelsea(SportEvent sportEvent) {
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

        bet = new Bet();
        bet.setSportEvent(sportEvent);
        bet.setDescription("winner");
        possibleOutcomes.add("Arsenal");
        possibleOutcomes.add("Chelsea");
        possibleOutcomes.add("Draw");
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
            outcome.setOutcomeOdds(generateOutcomeOdds(outcome));
            outcomes.add(outcome);
        });

        return outcomes;
    }

    private List<OutcomeOdd> generateOutcomeOdds(Outcome outcome) {
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
