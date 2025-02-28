package com.example.Customer.service;

import com.example.Customer.dto.UserDetailsRequestDto;
import com.example.Customer.dto.UserDetailsResponseDto;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UserDetailsResponseDto createUser(UserDetailsRequestDto userDetailsRequestDto);
    List<UserDetailsResponseDto> getAllUsers();
    UserDetailsResponseDto getUserById(String id);
    UserDetailsResponseDto updateUser(String id, UserDetailsRequestDto userDetailsRequestDto);
    void deleteUser(String id);
}
