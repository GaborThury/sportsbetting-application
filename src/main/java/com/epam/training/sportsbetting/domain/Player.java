package com.epam.training.sportsbetting.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PLAYER")
public class Player extends User {

    @Column
    private String name;

    @Column
    private Integer accountNumber;

    @Column
    private BigDecimal balance;

    @Column
    private Currency currency;

    @Column
    private LocalDate birth;
}
