package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bet {
    private String description;
    private BetType type;
    private List<Outcome> outcomes;
}
