package com.example.User.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(String token) {
}
