package com.example.Customer.controller;

import com.example.Customer.constants.UserConstants;
import com.example.Customer.dto.*;
import com.example.Customer.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "Auth", description = "Endpoints to manage auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Register a user",
            description = "Register a user with the given username, name, email, address, country"
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> createUser(
            @Valid @RequestBody final UserDetailsRequestDto registrationDto){
        UserDetailsResponseDto userDetailsResponseDto = userService.createUser(registrationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(UserConstants.STATUS_201, UserConstants.MESSAGE_201, userDetailsResponseDto));
    }

}
