package com.example.booting.DTO;

public record AuthResponse(
        String token,
        String tokenType
) {
}
