package com.example.Hotel.dto;

import com.example.Hotel.enums.Facilities;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HotelRequestDto(
        @NotBlank(message = "Hotel name cannot be blank")
        @Size(max = 100, message = "Hotel name must be at most 100 characters")
        String hotelName,

        List<Facilities> facilities,

        @NotBlank(message = "Hotel address cannot be blank")
        @Size(max = 200, message = "Hotel address must be at most 200 characters")
        String hotelAddress
) {
}
