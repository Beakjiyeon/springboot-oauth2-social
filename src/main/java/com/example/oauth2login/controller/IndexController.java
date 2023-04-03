package com.example.oauth2login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @GetMapping("")
    public String feed() {
        return "index.mustache";
    }

}
