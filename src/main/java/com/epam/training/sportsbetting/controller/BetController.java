package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.service.domainService.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
