package com.example.Customer.controller;

import com.example.Customer.constants.UserConstants;
import com.example.Customer.dto.RegistrationRequestDto;
import com.example.Customer.dto.RegistrationResponseDto;
import com.example.Customer.dto.ResponseDto;
import com.example.Customer.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "Auth", description = "Endpoints to manage auth")
public class UserController {

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Register a user",
            description = "Register a user with the given username, name, email, address, country"
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<RegistrationResponseDto>> createUser(
            @Valid @RequestBody final RegistrationRequestDto registrationDto){
        RegistrationResponseDto registrationResponseDto = userService.createUser(registrationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(UserConstants.STATUS_201, UserConstants.MESSAGE_201, registrationResponseDto));
    }
}
