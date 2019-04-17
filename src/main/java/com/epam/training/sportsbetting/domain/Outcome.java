package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Outcome {
    private Bet bet;
    private String description;
    private List<OutcomeOdd> outcomeOdds;

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equals(bet, outcome.bet) &&
                Objects.equals(description, outcome.description) &&
                Objects.equals(outcomeOdds, outcome.outcomeOdds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet, description, outcomeOdds);
    }*/
}
