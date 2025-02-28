package com.example.Customer.service.impl;

import com.example.Customer.dto.UserDetailsRequestDto;
import com.example.Customer.dto.UserDetailsResponseDto;
import com.example.Customer.dto.mapper.RegistrationMapper;
import com.example.Customer.exceptions.ResourceAlreadyExistsException;
import com.example.Customer.model.User;
import com.example.Customer.repository.UserRepository;
import com.example.Customer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public UserDetailsResponseDto createUser(UserDetailsRequestDto userDetailsRequestDto) {
        User user = RegistrationMapper.INSTANCE.registrationRequestDtoToUser(userDetailsRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByUsername(userDetailsRequestDto.username()) ||
                userRepository.existsByEmail(userDetailsRequestDto.email())) {
            throw new ResourceAlreadyExistsException("User","id", userDetailsRequestDto.username());
        }
        User savedUser = userRepository.save(user);
        return RegistrationMapper.INSTANCE.userToRegistrationResponseDto(savedUser);
    }

    @Override
    public List<UserDetailsResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(RegistrationMapper.INSTANCE::userToRegistrationResponseDto)
                .toList();
    }

    @Override
    public UserDetailsResponseDto getUserById(String id) {
        return userRepository.findById(id)
                .map(RegistrationMapper.INSTANCE::userToRegistrationResponseDto)
                .orElseThrow(() -> new ResourceAlreadyExistsException("User","id", id));
    }

    @Override
    public UserDetailsResponseDto updateUser(String id, UserDetailsRequestDto userDetailsRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceAlreadyExistsException("User","id", id));
        user.setUsername(userDetailsRequestDto.username());
        user.setName(userDetailsRequestDto.name());
        user.setEmail(userDetailsRequestDto.email());
        user.setPassword(passwordEncoder.encode(userDetailsRequestDto.password()));
        user.setAddress(userDetailsRequestDto.address());
        user.setCountry(userDetailsRequestDto.country());
        user.setAge(userDetailsRequestDto.age());

        User updatedUser = userRepository.save(user);
        return RegistrationMapper.INSTANCE.userToRegistrationResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceAlreadyExistsException("User","id", id));
        userRepository.delete(user);
    }

}
