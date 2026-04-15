package com.example.booting.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/profile")
    public Map<String, String> profile(Authentication authentication) {
        return Map.of(
                "message", "Protected endpoint access granted",
                "user", authentication.getName()
        );
    }
}
