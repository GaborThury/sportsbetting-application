package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Bet {
    private String description;
    private BetType type;
    private List<Outcome> outcomes;
}
