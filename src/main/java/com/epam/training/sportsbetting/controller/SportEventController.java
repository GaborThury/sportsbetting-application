package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.service.domainService.SportEventService;
import com.epam.training.sportsbetting.service.dto.SportEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportevents")
public class SportEventController {

    @Autowired
    private SportEventService sportEventService;

    @PostMapping("/add/")
    public ResponseEntity sportEventAdminPage(@ModelAttribute SportEventDto sportEventDto) {
        return ResponseEntity.ok(sportEventService.save(sportEventDto));
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
