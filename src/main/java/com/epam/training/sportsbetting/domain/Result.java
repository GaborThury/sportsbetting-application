package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result {
    private List<Outcome> winnerOutcomes;
}
