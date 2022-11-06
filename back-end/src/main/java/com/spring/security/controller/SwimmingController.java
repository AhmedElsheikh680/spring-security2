package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swimming")
public class SwimmingController {

    @DeleteMapping("/start")
    public ResponseEntity<String> start(){
        return ResponseEntity.ok("This Is Swimming COntroller");
    }
}
