package com.example.Hotel.service;

import com.example.Hotel.dto.PackageRequestDto;
import com.example.Hotel.dto.PackageResponseDto;
import com.example.Hotel.dto.PackageUpdateRequestDto;

import java.util.List;

public interface IPackageService {
    PackageResponseDto createPackage(PackageRequestDto packageRequestDto);
    PackageResponseDto getPackageById(String id);
    List<PackageResponseDto> getAllPackages();
    PackageResponseDto updatePackage(String id, PackageUpdateRequestDto packageRequestDto);
    void deletePackage(String id);
}
