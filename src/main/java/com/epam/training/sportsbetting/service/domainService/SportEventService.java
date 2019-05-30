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

    @Autowired
    private BetService betService;

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
        sportEventDTO.setType(sportEvent.getType());
        sportEventDTO.setTitle(sportEvent.getTitle());
        sportEventDTO.setStartDate(sportEvent.getStartDate());
        sportEventDTO.setEndDate(sportEvent.getEndDate());
        return sportEventDTO;
    }

    public SportEventDto findById(Integer id) {
        return toDto(sportEventRepository.findById(id).orElse(null)) ;
    }

    public SportEvent save(SportEventDto sportEventDto) {
        SportEvent entity = toEntity(sportEventDto);
        return sportEventRepository.save(entity);
    }

    private SportEvent toEntity(SportEventDto sportEventDto) {
        if (sportEventDto == null) return null;
        SportEvent sportEvent = new SportEvent();

        //sportEvent.setId(sportEventDto.getId());
        sportEvent.setType(sportEventDto.getType());
        sportEvent.setTitle(sportEventDto.getTitle());
        sportEvent.setStartDate(sportEventDto.getStartDate());
        sportEvent.setEndDate(sportEventDto.getEndDate());

        if (sportEventDto.getBetIds() != null && !sportEventDto.getBetIds().isEmpty()) {
            sportEvent.setBets(sportEventDto.getBetIds()
                    .stream()
                    .map(betService::findById)
                    .collect(Collectors.toList())
            );
        }
        return sportEvent;
    }
}
