package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

import java.util.List;

public class BettingService implements SportBettingService {

    private List<SportEvent> testDatas = null;

    @Override
    public void savePlayer(Player player) {

    }

    @Override
    public Player findPlayer() {
        return null;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        if (testDatas == null) {
            TestDataCreator testDataCreator = TestDataCreator.getInstance();
            testDatas = testDataCreator.getSportEvents();
        }
        return testDatas;
    }


    @Override
    public void saveWager(Wager wager) {

    }

    @Override
    public List<Wager> findAllWagers() {
        return null;
    }

    @Override
    public void calculateResults() {

    }
}
