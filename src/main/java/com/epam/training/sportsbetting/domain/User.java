package com.epam.training.sportsbetting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String password;
}
