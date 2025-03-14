package com.example.Hotel.dto.mapper;

import com.example.Hotel.dto.RoomRequestDto;
import com.example.Hotel.dto.RoomResponseDto;
import com.example.Hotel.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomDtoMapper {
    RoomDtoMapper INSTANCE = Mappers.getMapper(RoomDtoMapper.class);

//    @Mapping(target = "hotel.rooms", ignore = true)
    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(target = "packageIds", expression = "java(room.getPackages() != null ? room.getPackages().stream().map(com.example.Hotel.model.Packages::getId).toList() : java.util.Collections.emptyList())")
    RoomResponseDto roomToRoomResponseDto(Room room);

    Room roomRequestDtoToRoom(RoomRequestDto roomRequestDto);

}
