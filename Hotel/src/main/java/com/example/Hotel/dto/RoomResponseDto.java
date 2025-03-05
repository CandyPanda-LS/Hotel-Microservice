package com.example.Hotel.dto;

import com.example.Hotel.enums.Amenities;

import java.util.List;


public record RoomResponseDto(
        String id,
        String roomName,
        List<Amenities> amenities,
        String hotelId
) {
}
