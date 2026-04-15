package com.example.booting.auth.dto;

public record AuthResponse(
        String token,
        String tokenType
) {
}
