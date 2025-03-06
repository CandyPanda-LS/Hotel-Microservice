package com.example.User.service;

import com.example.User.dto.UserDetailsRequestDto;
import com.example.User.dto.UserDetailsResponseDto;

import java.util.List;

public interface IUserService {
    UserDetailsResponseDto createUser(UserDetailsRequestDto userDetailsRequestDto);
    List<UserDetailsResponseDto> getAllUsers();
    UserDetailsResponseDto getUserById(String id);
    UserDetailsResponseDto updateUser(String id, UserDetailsRequestDto userDetailsRequestDto);
    void deleteUser(String id);
}
