package com.epam.training.sportsbetting.service.dto;

import com.epam.training.sportsbetting.domain.BetType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BetDto {
    private Integer id;
    private String sportevent;
    private String description;
    private BetType type;
    private List<String> outcome;
}
