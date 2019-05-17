package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.Wager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagerRepository extends JpaRepository<Wager, Integer> {
}
