package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveTestDataToDB {

    private SportEventRepository sportEventRepository;
    private BetRepository betRepository;
    private OutcomeRepository outcomeRepository;
    private OutcomeOddRepository outcomeOddRepository;

    @Autowired
    public SaveTestDataToDB(BetRepository betRepository, OutcomeOddRepository outcomeOddRepository,
                            OutcomeRepository outcomeRepository,SportEventRepository sportEventRepository) {
        this.sportEventRepository = sportEventRepository;
        this.betRepository = betRepository;
        this.outcomeRepository = outcomeRepository;
        this.outcomeOddRepository = outcomeOddRepository;

        List<SportEvent> testDatas = TestDataCreator.getInstance().getSportEvents();
        save(testDatas);
    }

    public void save(List<SportEvent> sportEvents) {
        sportEvents.forEach(sportEvent -> {
            sportEventRepository.save(sportEvent);
            sportEvent.getBets().forEach(bet -> {
                betRepository.save(bet);
                bet.getOutcomes().forEach(outcome -> {
                    outcomeRepository.save(outcome);
                    outcome.getOutcomeOdds().forEach(outcomeOddRepository::save);
                });
            });
        });
    }
}
