package com.example.Customer.service;

import com.example.Customer.dto.RegistrationRequestDto;
import com.example.Customer.dto.RegistrationResponseDto;

public interface IUserService {
    RegistrationResponseDto createUser(RegistrationRequestDto registrationRequestDto);
}
