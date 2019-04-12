package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Outcome {
    private String description;
    private OutcomeOdd outcomeOdd;
}
