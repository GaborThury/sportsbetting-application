package com.epam.training.sportsbetting.repository;

import com.epam.training.sportsbetting.domain.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportEventRepository extends JpaRepository<SportEvent, Integer> {
    Optional<SportEvent> findById(Integer id);
}
