package com.example.Hotel.controller;

import com.example.Hotel.constants.HotelConstants;
import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;
import com.example.Hotel.dto.ResponseDto;
import com.example.Hotel.service.IHotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/hotel", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class HotelController {
    @Autowired
    IHotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getHotels(){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelService.getAllHotels()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<HotelResponseDto>> getHotelById(@PathVariable String id){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelService.getHotelById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<HotelResponseDto>> createHotel(@Valid @RequestBody final HotelRequestDto hotelDetailsRequestDto){
        HotelResponseDto hotelResponseDto = hotelService.createHotel(hotelDetailsRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(HotelConstants.STATUS_201, HotelConstants.MESSAGE_201, hotelResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<HotelResponseDto>> updateHotel(@PathVariable String id, @Valid @RequestBody final HotelRequestDto hotelDetailsRequestDto){
        HotelResponseDto hotelResponseDto = hotelService.updateHotel(id, hotelDetailsRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelResponseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteHotel(@PathVariable String id){
        hotelService.deleteHotel(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, "Hotel deleted successfully"));
    }

}
