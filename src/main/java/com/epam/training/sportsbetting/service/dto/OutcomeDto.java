package com.epam.training.sportsbetting.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OutcomeDto {
    private Integer id;
    private String betDescription;
    private String description;
    private Boolean isWon;
    private List<BigDecimal> outcomeOddValues;
}
