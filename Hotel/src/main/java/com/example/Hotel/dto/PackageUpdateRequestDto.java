package com.example.Hotel.dto;

import com.example.Hotel.enums.BoardBasis;

import java.math.BigDecimal;

public record PackageUpdateRequestDto(
    String packageName,
    BigDecimal price,
    BigDecimal tax,
    Integer occupancy,
    BoardBasis boardBasis
){
}
