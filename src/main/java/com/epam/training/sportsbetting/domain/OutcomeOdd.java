package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OutcomeOdd {
    private Outcome outcome;
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
}
