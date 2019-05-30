package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.service.domainService.BetService;
import com.epam.training.sportsbetting.service.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @GetMapping("/")
    public ResponseEntity<List> bets() {
        return ResponseEntity.ok(betService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBetById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(betService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createBet(@RequestBody BetDto betDto) {
        return ResponseEntity.ok(betService.save(betDto));
    }
}
