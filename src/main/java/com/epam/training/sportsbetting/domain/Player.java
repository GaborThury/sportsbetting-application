package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class Player extends User {
    @Id
    @GeneratedValue
    private Integer id;

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
