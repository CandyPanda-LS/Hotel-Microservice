package com.example.Customer.dto;

public record ResponseDto<T>(

        String statusCode,

        String statusMsg,

        T data
) {}
