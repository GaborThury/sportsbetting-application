package com.epam.training.sportsbetting.service.domainService;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player save(Player player) {
        return playerRepository.save(player);
    }


}
