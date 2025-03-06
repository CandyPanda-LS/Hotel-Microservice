package com.example.User.dto.mapper;

import com.example.User.dto.UserDetailsRequestDto;
import com.example.User.dto.UserDetailsResponseDto;
import com.example.User.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    UserDetailsResponseDto userToRegistrationResponseDto(User user);

    User registrationRequestDtoToUser(UserDetailsRequestDto userDetailsRequestDto);


}
