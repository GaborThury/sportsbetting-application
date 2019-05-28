package com.epam.training.sportsbetting.service.domainService;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.service.dto.SportEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportEventService {

    @Autowired
    private SportEventRepository sportEventRepository;

    public List<SportEventDto> findAll() {
        return toDto(sportEventRepository.findAll());
    }

    private List<SportEventDto> toDto(List<SportEvent> sportEvents) {
        return sportEvents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public SportEventDto toDto(SportEvent sportEvent) {
        if (sportEvent == null) {
            return null;
        }

        SportEventDto sportEventDTO = new SportEventDto();

        sportEventDTO.setId(sportEvent.getId());
        sportEventDTO.setTitle(sportEvent.getTitle());
        sportEventDTO.setStartDate(sportEvent.getStartDate());
        sportEventDTO.setEndDate(sportEvent.getEndDate());
        return sportEventDTO;
    }

    public SportEventDto findById(Integer id) {
        return toDto(sportEventRepository.findById(id).orElse(null)) ;
    }
}
