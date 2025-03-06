package com.example.Customer.dto.mapper;

import com.example.Customer.dto.UserDetailsRequestDto;
import com.example.Customer.dto.UserDetailsResponseDto;
import com.example.Customer.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    UserDetailsResponseDto userToRegistrationResponseDto(User user);

    User registrationRequestDtoToUser(UserDetailsRequestDto userDetailsRequestDto);


}
