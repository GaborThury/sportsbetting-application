package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.SportEventRepository;
import com.epam.training.sportsbetting.service.domainService.SaveTestDataToDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BettingService implements SportBettingService {

    @Autowired
    SportEventRepository sportEventRepository;

    @Autowired
    SaveTestDataToDB saveTestDataToDB;

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
            testDatas = TestDataCreator.getInstance().getSportEvents();
            //testDatas.forEach(sportEvent -> sportEventRepository.save(sportEvent));
            saveTestDataToDB.save(testDatas);
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
