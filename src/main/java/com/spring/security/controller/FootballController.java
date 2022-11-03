package com.spring.security.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football")
public class FootballController {

    @GetMapping("/start")
    public ResponseEntity<String> start(){
        return ResponseEntity.ok("This Is Football Controller");
    }
}