package com.epam.training.sportsbetting.service.domainService;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.repository.OutcomeRepository;
import com.epam.training.sportsbetting.service.dto.OutcomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomeService {

    @Autowired
    private OutcomeRepository outcomeRepository;

    public Outcome findOneDirect(Integer id) {
        return outcomeRepository.findById(id).orElse(null);
    }
}
