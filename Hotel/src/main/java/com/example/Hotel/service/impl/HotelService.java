package com.example.Hotel.service.impl;

import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;
import com.example.Hotel.dto.mapper.HotelDtoMapper;
import com.example.Hotel.exceptions.ResourceAlreadyExistsException;
import com.example.Hotel.exceptions.ResourceNotFoundException;
import com.example.Hotel.model.Hotel;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public HotelResponseDto createHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = HotelDtoMapper.INSTANCE.hotelRequestDtoToHotel(hotelRequestDto);
        if (hotelRepository.existsByHotelName(hotel.getHotelName())) {
            throw new ResourceAlreadyExistsException("Hotel","name", hotel.getHotelName());
        }
        Hotel savedHotel = hotelRepository.save(hotel);
        return HotelDtoMapper.INSTANCE.hotelToHotelResponseDto(savedHotel);
    }

    @Override
    public HotelResponseDto getHotelById(String id) {
        return hotelRepository.findById(id)
                .map(HotelDtoMapper.INSTANCE::hotelToHotelResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel","id", id));
    }

    @Override
    public List<HotelResponseDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(HotelDtoMapper.INSTANCE::hotelToHotelResponseDto)
                .toList();
    }

    @Override
    public HotelResponseDto updateHotel(String id, HotelRequestDto hotelRequestDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel","id", id));
        hotel.setHotelName(hotelRequestDto.hotelName());
        hotel.setAddress(hotelRequestDto.hotelAddress());
        hotel.setFacilities(hotelRequestDto.facilities());

        Hotel updatedHotel = hotelRepository.save(hotel);
        return HotelDtoMapper.INSTANCE.hotelToHotelResponseDto(updatedHotel);
    }

    @Override
    public void deleteHotel(String id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel","id", id));
        hotelRepository.delete(hotel);
    }
}
