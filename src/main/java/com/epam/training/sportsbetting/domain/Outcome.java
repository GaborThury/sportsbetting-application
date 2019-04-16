package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Outcome {
    private Bet bet;
    private String description;
    private List<OutcomeOdd> outcomeOdds;
}
