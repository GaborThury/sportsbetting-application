package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Component
@Scope("prototype")
public class Wager {
    private Player player;
    private OutcomeOdd outcomeOdd;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;

    public Outcome getOutcome() {
        return this.getOutcomeOdd().getOutcome();
    }
    public SportEvent getSportEvent() {
        return this.getOutcomeOdd().getOutcome().getBet().getSportEvent();
    }
}
