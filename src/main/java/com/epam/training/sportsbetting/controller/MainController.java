package com.epam.training.sportsbetting.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String index() {
        return "Welcome! :)";
    }
}
