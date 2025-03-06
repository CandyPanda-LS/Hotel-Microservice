package com.example.Hotel.service;

import com.example.Hotel.dto.RoomRequestDto;
import com.example.Hotel.dto.RoomResponseDto;
import com.example.Hotel.dto.RoomUpdateRequestDto;

import java.util.List;

public interface IRoomService {
    RoomResponseDto createRoom(RoomRequestDto roomRequestDto);
    RoomResponseDto getRoomById(String id);
    List<RoomResponseDto> getAllRooms();
    RoomResponseDto updateRoom(String id, RoomUpdateRequestDto roomRequestDto);
    void deleteRoom(String id);
}
