package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BettingService implements SportBettingService {

    private SportEventRepository sportEventRepository;
    private PlayerRepository playerRepository;
    private WagerRepository wagerRepository;

    public BettingService(SportEventRepository sportEventRepository, PlayerRepository playerRepository, WagerRepository wagerRepository) {
        this.sportEventRepository = sportEventRepository;
        this.playerRepository = playerRepository;
        this.wagerRepository = wagerRepository;
    }

    @Override
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Player findPlayer() {
        return null;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return sportEventRepository.findAll();
    }

    @Override
    public void saveWager(Wager wager) {
        wagerRepository.save(wager);
    }

    @Override
    public List<Wager> findAllWagers() {
        return wagerRepository.findAll();
    }

    @Override
    public void calculateResults() {

    }
}
