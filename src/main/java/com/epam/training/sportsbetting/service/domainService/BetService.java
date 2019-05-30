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

    public List<BetDto> findAll() {
        return toDto(betRepository.findAll());
    }

    public Bet findById(Integer id) {
        return betRepository.findById(id).orElse(null);
    }

    private List<BetDto> toDto(List<Bet> bets) {
        return bets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private BetDto toDto(Bet bet) {
        if (bet == null) {
            return null;
        }
        BetDto betDTO = new BetDto();

        betDTO.setId(bet.getId());
        betDTO.setSportevent(bet.getSportEvent());
        betDTO.setDescription(bet.getDescription());
        betDTO.setType(bet.getType());
        betDTO.setOutcome(bet.getOutcomes()
                .stream()
                .map(Outcome::getDescription)
                .collect(Collectors.toList()));

        return betDTO;
    }


}
