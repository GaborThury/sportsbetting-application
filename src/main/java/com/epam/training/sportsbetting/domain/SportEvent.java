package com.epam.training.sportsbetting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "SPORTEVENT")
public class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    @Column
    private String title;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @JsonIgnore
    @OneToMany(mappedBy = "sportEvent", fetch = FetchType.EAGER)
    private List<Bet> bets = new ArrayList<>();

}
