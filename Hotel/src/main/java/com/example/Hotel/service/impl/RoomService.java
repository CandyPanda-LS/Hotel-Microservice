package com.example.Hotel.service.impl;

import com.example.Hotel.dto.RoomRequestDto;
import com.example.Hotel.dto.RoomResponseDto;
import com.example.Hotel.dto.RoomUpdateRequestDto;
import com.example.Hotel.dto.mapper.HotelDtoMapper;
import com.example.Hotel.dto.mapper.RoomDtoMapper;
import com.example.Hotel.exceptions.ResourceAlreadyExistsException;
import com.example.Hotel.model.Hotel;
import com.example.Hotel.model.Room;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.repository.RoomRepository;
import com.example.Hotel.service.IRoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements IRoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public RoomResponseDto createRoom(RoomRequestDto roomRequestDto) {
        Hotel hotel = hotelRepository.findById(roomRequestDto.hotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        if (roomRepository.existsByRoomNameAndHotel(roomRequestDto.roomName(),hotel)){
            throw new ResourceAlreadyExistsException("Room","name", roomRequestDto.roomName());
        }
        Room room = RoomDtoMapper.INSTANCE.roomRequestDtoToRoom(roomRequestDto);
        room.setHotel(hotel);
        Room savedRoom = roomRepository.save(room);
        RoomResponseDto roomResponseDto = RoomDtoMapper.INSTANCE.roomToRoomResponseDto(savedRoom);
        return roomResponseDto;

    }

    @Override
    public RoomResponseDto getRoomById(String id) {
        return null;
    }

    @Override
    public RoomResponseDto updateRoom(String id, RoomUpdateRequestDto roomRequestDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        room.setRoomName(roomRequestDto.roomName());
        room.setAmenities(roomRequestDto.amenities());
        Room updatedRoom = roomRepository.save(room);
        return RoomDtoMapper.INSTANCE.roomToRoomResponseDto(updatedRoom);
    }

    @Override
    public void deleteRoom(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        roomRepository.delete(room);
    }
}
