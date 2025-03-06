package com.example.User.dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
