package com.example.Customer.dto;

public record RegistrationResponseDto(
        String id,
        String username,
        String name,
        String email,
        String address,
        String country,
        Integer age,
        String sex
) {
}

