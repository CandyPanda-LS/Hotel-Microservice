package com.example.User.service.impl;

import com.example.User.dto.UserDetailsRequestDto;
import com.example.User.dto.UserDetailsResponseDto;
import com.example.User.dto.mapper.RegistrationMapper;
import com.example.User.exceptions.ResourceAlreadyExistsException;
import com.example.User.exceptions.ResourceNotFoundException;
import com.example.User.model.User;
import com.example.User.repository.UserRepository;
import com.example.User.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (userRepository.existsByUsername(userDetailsRequestDto.username()) ||
                userRepository.existsByEmail(userDetailsRequestDto.email())) {
            throw new ResourceAlreadyExistsException("User","id", userDetailsRequestDto.username());
        }
        User user = RegistrationMapper.INSTANCE.registrationRequestDtoToUser(userDetailsRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
                .orElseThrow(() -> new ResourceNotFoundException("User","id", id));
    }

    @Override
    public UserDetailsResponseDto updateUser(String id, UserDetailsRequestDto userDetailsRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","id", id));
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
                .orElseThrow(() -> new ResourceNotFoundException("User","id", id));
        userRepository.delete(user);
    }

}
