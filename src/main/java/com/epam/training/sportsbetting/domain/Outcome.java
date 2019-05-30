package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "OUTCOME")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;

    @Column
    private String description;

    @Column
    private Boolean isWon;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OutcomeOdd> outcomeOdds = new ArrayList<>();
}
