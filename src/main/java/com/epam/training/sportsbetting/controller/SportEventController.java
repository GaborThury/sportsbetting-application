package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.service.domainService.SportEventService;
import com.epam.training.sportsbetting.service.dto.SportEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sportevents")
public class SportEventController {

    @Autowired
    private SportEventService sportEventService;

    @GetMapping("/add")
    public ResponseEntity sportEventAdminPage() {
        return ResponseEntity.ok("you will be able to add sport events on this page on the future...");
    }

    @GetMapping("/")
    public ResponseEntity<List> sportevents() {
        return ResponseEntity.ok(sportEventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportEventDto> getSingleSportevent(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(sportEventService.findById(id));
    }

}
