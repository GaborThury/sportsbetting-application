package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@Entity
@Table(name = "WAGER")
public class Wager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "outcomeOdd_id")
    private OutcomeOdd outcomeOdd;

    @Column
    private BigDecimal amount;

    @Column
    private Currency currency;

    @Column
    private LocalDateTime timestampCreated;

    @Column
    private boolean processed;

    @Column
    private boolean win;

    public Outcome getOutcome() {
        return this.getOutcomeOdd().getOutcome();
    }
    public SportEvent getSportEvent() {
        return this.getOutcomeOdd().getOutcome().getBet().getSportEvent();
    }
}
