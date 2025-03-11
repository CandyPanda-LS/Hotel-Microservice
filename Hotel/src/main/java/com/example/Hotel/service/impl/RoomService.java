package com.example.Hotel.service.impl;

import com.example.Hotel.dto.RoomAvailabilityRequestDto;
import com.example.Hotel.dto.RoomRequestDto;
import com.example.Hotel.dto.RoomResponseDto;
import com.example.Hotel.dto.RoomUpdateRequestDto;
import com.example.Hotel.dto.mapper.HotelDtoMapper;
import com.example.Hotel.dto.mapper.RoomDtoMapper;
import com.example.Hotel.enums.RoomStatus;
import com.example.Hotel.exceptions.ResourceAlreadyExistsException;
import com.example.Hotel.model.Hotel;
import com.example.Hotel.model.Room;
import com.example.Hotel.model.RoomAvailability;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.repository.RoomAvailabilityRepository;
import com.example.Hotel.repository.RoomRepository;
import com.example.Hotel.service.IRoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomAvailabilityRepository roomAvailabilityRepository;

    @Override
    public RoomResponseDto createRoom(RoomRequestDto roomRequestDto) {
        Hotel hotel = hotelRepository.findById(roomRequestDto.hotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        if (roomRepository.existsByRoomNameAndHotel(roomRequestDto.roomName(),hotel)){
            throw new ResourceAlreadyExistsException("Room","name", roomRequestDto.roomName());
        }
        Room room = RoomDtoMapper.INSTANCE.roomRequestDtoToRoom(roomRequestDto);
        room.setHotel(hotel);

        List<RoomAvailability> roomAvailabilities = new ArrayList<>();
        LocalDate weekBeforeToday = LocalDate.now().minusWeeks(1);
        LocalDate twoWeeksFromToday = LocalDate.now().plusWeeks(2);

        for (LocalDate date = weekBeforeToday; date.isBefore(twoWeeksFromToday); date = date.plusDays(1)) {
            RoomAvailability roomAvailability = new RoomAvailability();
            roomAvailability.setDate(date);
            roomAvailability.setRoom(room);
            roomAvailability.setStatus(RoomStatus.AVAILABLE);
            roomAvailabilities.add(roomAvailability);
        }

        room.setAvailability(roomAvailabilities);

        Room savedRoom = roomRepository.save(room);
        return RoomDtoMapper.INSTANCE.roomToRoomResponseDto(savedRoom);

    }

    @Override
    public RoomResponseDto getRoomById(String id) {
        return roomRepository.findById(id)
                .map(RoomDtoMapper.INSTANCE::roomToRoomResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
    }

    @Override
    public List<RoomResponseDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(RoomDtoMapper.INSTANCE::roomToRoomResponseDto)
                .toList();
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
    @Transactional
    public void updateRoomAvailabilities(String id, List<RoomAvailabilityRequestDto> requestDtos) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        for (RoomAvailabilityRequestDto requestDto : requestDtos) {
            RoomAvailability availability = roomAvailabilityRepository.findByRoomAndDate(room, requestDto.date())
                    .orElseThrow(() -> new EntityNotFoundException("Availability not found for room " + id + " on date " + requestDto.date()));
            availability.setStatus(requestDto.status());
            roomAvailabilityRepository.save(availability);
        }
    }

    @Transactional
    public void cronUpdateRoomAvailabilities(){
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            LocalDate latestDate = roomAvailabilityRepository.findLatestDateByRoom(room)
                    .orElseThrow(() -> new RuntimeException("No availability found for room: " + room.getId()));
            LocalDate earliestDate = roomAvailabilityRepository.findEarliestDateByRoom(room)
                    .orElseThrow(() -> new RuntimeException("No availability found for room: " + room.getId()));
            if (earliestDate.isAfter(LocalDate.now().minusWeeks(1))){
                continue;
            }
            LocalDate nextDay = latestDate.plusDays(1);
            RoomAvailability newAvailability = new RoomAvailability();
            newAvailability.setRoom(room);
            newAvailability.setDate(nextDay);
            newAvailability.setStatus(RoomStatus.AVAILABLE);
            roomAvailabilityRepository.save(newAvailability);
            roomAvailabilityRepository.deleteByRoomAndDate(room, earliestDate);
        }
    }

    @Override
    public void deleteRoom(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        roomRepository.delete(room);
    }
}
