package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.util.List;

public class Service implements SportBettingService {
    @Override
    public void savePlayer(Player player) {

    }

    @Override
    public Player findPlayer() {
        return null;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        CreateTestDatas createTestDatas = new CreateTestDatas();
        return createTestDatas.generateSportEvents();
    }

    @Override
    public void saveWager(Wager wager) {

    }

    @Override
    public List<Wager> findallWagers() {
        return null;
    }

    @Override
    public void calculateResults() {

    }
}
