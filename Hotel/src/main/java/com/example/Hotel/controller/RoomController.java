package com.example.Hotel.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class RoomController {

}
