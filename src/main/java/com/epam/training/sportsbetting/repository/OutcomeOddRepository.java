package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeOddRepository extends JpaRepository<OutcomeOdd, Integer> {
}
