package com.example.Hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Amenities {
    AIR_CONDITIONING("AIR_CONDITIONING"),
    WIFI("WIFI"),
    TV("TV"),
    MINI_BAR("MINI_BAR");

    private final String amenity;
}
