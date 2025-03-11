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

    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "updatedBy")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "hotel")
    @Mapping(ignore = true, target = "packages")
    @Mapping(ignore = true, target = "availability")
    Room roomRequestDtoToRoom(RoomRequestDto roomRequestDto);

}
