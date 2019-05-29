package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.service.domainService.BetService;
import com.epam.training.sportsbetting.service.domainService.SportEventService;
import com.epam.training.sportsbetting.service.domainService.UserService;
import com.epam.training.sportsbetting.service.dto.SportEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private SportEventService sportEventService;

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "Welcome! :)";
    }


    @PostMapping("/admin/{i}")
    public Integer addSportEvent(@PathVariable(value = "i") Integer i) {
        return i;
    }

    @GetMapping("/admin/addsportevent")
    public ResponseEntity sportEventAdminPage() {
        return ResponseEntity.ok("jeló");
    }

    @GetMapping("/sportevents")
    public ResponseEntity<List> sportevents() {
        return ResponseEntity.ok(sportEventService.findAll());
    }

    @GetMapping("/sportevents/{id}")
    public ResponseEntity<SportEventDto> getSingleSportevent(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(sportEventService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/bets")
    public ResponseEntity<List> bets() {
        return ResponseEntity.ok(betService.findAll());
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@ModelAttribute User user) {
        userService.registerUser(user);
        return ResponseEntity.ok(null);
    }

}
