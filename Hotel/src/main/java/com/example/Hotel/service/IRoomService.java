package com.example.Hotel.service;

import com.example.Hotel.dto.RoomRequestDto;
import com.example.Hotel.dto.RoomResponseDto;
import com.example.Hotel.dto.RoomUpdateRequestDto;

public interface IRoomService {
    RoomResponseDto createRoom(RoomRequestDto roomRequestDto);
    RoomResponseDto getRoomById(String id);
    RoomResponseDto updateRoom(String id, RoomUpdateRequestDto roomRequestDto);
    void deleteRoom(String id);
}
