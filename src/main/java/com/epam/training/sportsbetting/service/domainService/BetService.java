package com.epam.training.sportsbetting.service.domainService;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.service.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private OutcomeService outcomeService;

    @Autowired
    private SportEventService sportEventService;

    public List<BetDto> findAll() {
        return toDto(betRepository.findAll());
    }

    public BetDto findById(Integer id) {
        return toDto(betRepository.findById(id).orElse(null));
    }

    public Bet findOneDirect(Integer id) {
        return betRepository.findById(id).orElse(null);
    }

    public BetDto save(BetDto betDto) {
        return toDto(betRepository.save(toEntity(betDto)));
    }

    private Bet toEntity(BetDto betDto) {
        if (betDto == null) return null;

        Bet bet = new Bet();
        bet.setSportEvent(sportEventService.findOneDirect(betDto.getSporteventId()));
        bet.setDescription(betDto.getDescription());
        bet.setType(betDto.getType());
        bet.setOutcomes(betDto.getOutcomeIds()
                .stream()
                .map(outcomeService::findOneDirect)
                .collect(Collectors.toList()));
        return bet;
    }

    private List<BetDto> toDto(List<Bet> bets) {
        return bets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private BetDto toDto(Bet bet) {
        if (bet == null) return null;
        BetDto betDTO = new BetDto();

        betDTO.setId(bet.getId());
        betDTO.setSporteventId(bet.getSportEvent().getId());
        betDTO.setDescription(bet.getDescription());
        betDTO.setType(bet.getType());
        betDTO.setOutcomeIds(bet.getOutcomes()
                .stream()
                .map(Outcome::getId)
                .collect(Collectors.toList()));

        return betDTO;
    }
}
