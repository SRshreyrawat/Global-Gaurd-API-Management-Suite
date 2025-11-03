package com.Jovac_Project.Global_Guard_API_error_mangement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to Global Guard API Error Management Suite!";
    }

    @GetMapping("/test-not-found")
    public String testNotFound() {
        throw new jakarta.persistence.EntityNotFoundException("Test entity not found");
    }

    @GetMapping("/test-bad-request")
    public String testBadRequest() {
        throw new IllegalArgumentException("Invalid test parameter");
    }

    @GetMapping("/test-server-error")
    public String testServerError() {
        throw new RuntimeException("Test server error");
    }
}
