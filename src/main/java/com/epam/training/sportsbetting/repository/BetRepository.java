package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
}
