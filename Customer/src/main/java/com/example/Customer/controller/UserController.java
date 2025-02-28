package com.example.Customer.controller;

import com.example.Customer.constants.UserConstants;
import com.example.Customer.dto.ResponseDto;
import com.example.Customer.dto.UserDetailsRequestDto;
import com.example.Customer.dto.UserDetailsResponseDto;
import com.example.Customer.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "User", description = "Endpoints to manage auth")
public class UserController {

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Get all users",
            description = "Get all users"
    )
    @GetMapping("/users")
    public ResponseEntity<ResponseDto<List<UserDetailsResponseDto>>> getUsers(){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.getAllUsers()));
    }

    @Operation(
            summary = "Get user by id",
            description = "Get user by id"
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> getUserById(@PathVariable String id){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.getUserById(id)));
    }

    @Operation(
            summary = "Delete user by id",
            description = "Delete user by id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, "User deleted successfully"));
    }

    @Operation(
            summary = "Update user by id",
            description = "Update user by id"
    )
    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> updateUser(@PathVariable String id, @RequestBody UserDetailsRequestDto userDetailsRequestDto){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.updateUser(id,userDetailsRequestDto )));
    }

}
