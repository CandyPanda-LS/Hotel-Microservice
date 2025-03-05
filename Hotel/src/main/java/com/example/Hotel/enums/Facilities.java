package com.example.Hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Facilities {
    FREE_PARKING("FREE_PARKING"),
    SWIMMING_POOL("SWIMMING_POOL"),
    GYM("GYM"),
    SPA("SPA");

    private final String facility;

}
