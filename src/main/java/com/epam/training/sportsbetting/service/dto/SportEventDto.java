package com.epam.training.sportsbetting.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SportEventDto {
    private Integer id;
    private String type;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Integer> betIds;
}
