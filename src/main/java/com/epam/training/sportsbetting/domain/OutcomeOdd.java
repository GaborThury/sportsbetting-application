package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class OutcomeOdd {
    private int id;
    private BigDecimal value;
    private Outcome outcome;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
}
