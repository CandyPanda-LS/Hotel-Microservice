package com.example.Hotel.dto.mapper;

import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;
import com.example.Hotel.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HotelDtoMapper {
    HotelDtoMapper INSTANCE = Mappers.getMapper(HotelDtoMapper.class);

    HotelResponseDto hotelToHotelResponseDto(Hotel hotel);

    @Mapping(source = "hotelAddress", target = "address")
    Hotel hotelRequestDtoToHotel(HotelRequestDto hotelRequestDto);

}
