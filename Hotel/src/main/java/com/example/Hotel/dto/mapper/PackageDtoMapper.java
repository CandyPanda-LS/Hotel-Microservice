package com.example.Hotel.dto.mapper;

import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;
import com.example.Hotel.dto.PackageRequestDto;
import com.example.Hotel.dto.PackageResponseDto;
import com.example.Hotel.model.Hotel;
import com.example.Hotel.model.Packages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PackageDtoMapper {
    PackageDtoMapper INSTANCE = Mappers.getMapper(PackageDtoMapper.class);

    @Mapping(source = "id", target = "packageId")
    @Mapping(source = "room.id", target = "roomId")
    PackageResponseDto packageToPackageResponseDto(Packages packages);

    Packages packageDtoToPackage(PackageRequestDto packageRequestDto);

}
