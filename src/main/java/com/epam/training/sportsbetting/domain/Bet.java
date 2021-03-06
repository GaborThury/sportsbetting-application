package com.epam.training.sportsbetting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BET")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "sportevent_id")
    private SportEvent sportEvent;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 127)
    private BetType type;

    @OneToMany(mappedBy = "bet", fetch = FetchType.EAGER)
    private List<Outcome> outcomes = new ArrayList<>();
}
