package com.epam.training.sportsbetting.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum BetType {
    WINNER,
    GOALS,
    PLAYERS_SCORE,
    NUMBER_OF_SETS
}
