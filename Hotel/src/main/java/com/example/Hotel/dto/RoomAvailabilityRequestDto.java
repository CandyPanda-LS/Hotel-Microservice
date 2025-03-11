package com.example.Hotel.dto;

import com.example.Hotel.enums.RoomStatus;

import java.time.LocalDate;

public record RoomAvailabilityRequestDto(
        LocalDate date,
        RoomStatus status
) {
}
