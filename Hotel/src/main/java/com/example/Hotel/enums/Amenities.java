package com.example.Hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Amenities {
    Air_Conditioning("Air_Conditioning"),
    Wifi("Wifi"),
    Television("Television"),
    Mini_Bar("Mini_Bar");

    private final String amenity;
}
