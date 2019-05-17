package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
}
