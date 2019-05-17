package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "OUTCOMEODD")
public class OutcomeOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;

    @Column
    private LocalDateTime validFrom;

    @Column
    private LocalDateTime validUntil;
}
