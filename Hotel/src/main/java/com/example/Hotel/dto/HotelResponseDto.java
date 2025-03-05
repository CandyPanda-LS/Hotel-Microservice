package com.example.Hotel.dto;

import com.example.Hotel.enums.Facilities;
import com.example.Hotel.model.Reviews;
import com.example.Hotel.model.Room;

import java.util.List;

public record HotelResponseDto(
        String id,
        String hotelName,
        List<Facilities> facilities,
        List<Reviews> reviews,
        List<Room> rooms,
        String address
) {}