package com.epam.training.sportsbetting.controller;

import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.service.domainService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@ModelAttribute User user) {
        userService.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activation/{code}")
    public ResponseEntity activation(@PathVariable("code") String code) {
        try {
            userService.activateUser(code);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
