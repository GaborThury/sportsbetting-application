package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserBetService {
    private OutcomeOddRepository outcomeOddRepository;
    private OutcomeRepository outcomeRepository;
    private BetRepository betRepository;
    private WagerRepository wagerRepository;
    private PlayerRepository playerRepository;

    public UserBetService(OutcomeOddRepository outcomeOddRepository, OutcomeRepository outcomeRepository,
                          BetRepository betRepository, WagerRepository wagerRepository,
                          PlayerRepository playerRepository) {
        this.outcomeOddRepository = outcomeOddRepository;
        this.outcomeRepository = outcomeRepository;
        this.betRepository = betRepository;
        this.wagerRepository = wagerRepository;
        this.playerRepository = playerRepository;
    }

    public OutcomeOdd findOutcomeOddById(int id) {
        return outcomeOddRepository.findById(id).orElse(null);
    }


    public Wager createWager(Player player, BigDecimal wagerAmount, OutcomeOdd userOutcomeOdd) {
        Wager wager = new Wager();
        wager.setPlayer(player);
        wager.setAmount(wagerAmount);
        wager.setCurrency(player.getCurrency());
        wager.setOutcomeOdd(userOutcomeOdd);
        wager.setTimestampCreated(LocalDateTime.now());
        return wagerRepository.save(wager);
    }
/*
    public Result generateResult() {
        Result result = new Result();
        List<Outcome> winnerOutcomes = new ArrayList<>();

        betRepository.findAll().forEach(bet -> {
            int randomInt = getRandomNumberInRange(0, bet.getOutcomes().size() - 1);
            winnerOutcomes.add(bet.getOutcomes().get(randomInt));
        });

        result.setWinnerOutcomes(winnerOutcomes);
        return result;
    }*/

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

/*    public void summarizeResults(Result result, Player player) {
        List<Integer> winnerOutcomeIdS = result.getWinnerOutcomes()
                .stream()
                .map(Outcome::getId)
                .collect(Collectors.toList());

        wagerRepository.findByPlayerId(player.getId()).forEach(wager -> {
            if (wagerWon(winnerOutcomeIdS, wager)) {
                wager.setWin(true);
                updatePlayerBalance(player, wager);
            }
            wager.setProcessed(true);
            wagerRepository.save(wager);
        });
    }*/

    private void updatePlayerBalance(Player player, Wager wager) {
        player.setBalance(player.getBalance()
                .add(wager.getAmount().multiply(wager.getOutcomeOdd().getValue())));
        playerRepository.save(player);
    }

    private boolean wagerWon(List<Integer> winnerOutcomeIdS, Wager wager) {
        return winnerOutcomeIdS.contains(wager.getOutcomeOdd().getId());
    }

}
