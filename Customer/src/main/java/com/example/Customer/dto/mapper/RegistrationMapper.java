package com.example.Customer.dto.mapper;

import com.example.Customer.dto.RegistrationRequestDto;
import com.example.Customer.dto.RegistrationResponseDto;
import com.example.Customer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    RegistrationResponseDto userToRegistrationResponseDto(User user);

    User registrationRequestDtoToUser(RegistrationRequestDto registrationRequestDto);


}
