package com.example.Customer.service.impl;

import com.example.Customer.dto.RegistrationRequestDto;
import com.example.Customer.dto.RegistrationResponseDto;
import com.example.Customer.dto.mapper.RegistrationMapper;
import com.example.Customer.exceptions.ResourceAlreadyExistsException;
import com.example.Customer.model.User;
import com.example.Customer.repository.UserRepository;
import com.example.Customer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RegistrationResponseDto createUser(RegistrationRequestDto registrationRequestDto) {
        User user = RegistrationMapper.INSTANCE.registrationRequestDtoToUser(registrationRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByUsername(registrationRequestDto.username()) ||
                userRepository.existsByEmail(registrationRequestDto.email())) {
            throw new ResourceAlreadyExistsException("User","id", registrationRequestDto.username());
        }
        User savedUser = userRepository.save(user);
        return RegistrationMapper.INSTANCE.userToRegistrationResponseDto(savedUser);
    }
}
