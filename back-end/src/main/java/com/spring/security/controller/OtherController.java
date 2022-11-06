package com.spring.security.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {

    @PostMapping("/start")
    public String start(){
        return "Write What You See!!!!!!";
    }
}
