package com.example.Hotel.dto;

import com.example.Hotel.enums.Facilities;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record HotelRequestDto(
        String hotelName,
        List<Facilities> facilities,
        String hotelAddress
) {
}
