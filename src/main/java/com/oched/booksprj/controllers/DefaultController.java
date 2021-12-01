package com.oched.booksprj.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Books PRJ");
    }
}
