package com.example.Hotel.dto;

import com.example.Hotel.enums.Amenities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RoomUpdateRequestDto(
        @NotBlank(message = "Room name cannot be blank")
        @Size(max = 100, message = "Room name must be at most 100 characters")
        String roomName,
        List<Amenities> amenities
) {
}
