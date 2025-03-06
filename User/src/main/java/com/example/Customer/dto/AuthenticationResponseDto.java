package com.example.Customer.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(String token) {
}
