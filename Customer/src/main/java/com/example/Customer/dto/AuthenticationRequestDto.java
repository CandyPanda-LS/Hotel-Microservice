package com.example.Customer.dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
