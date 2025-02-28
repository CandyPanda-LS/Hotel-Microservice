package com.example.Customer.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public record RegistrationRequestDto(
        @NotNull(message = "Username cannot be null")
        @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
        String username,

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name,

        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email,

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "Address cannot be null")
        @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
        String address,

        @NotNull(message = "Country cannot be null")
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Country should only contain letters and spaces")
        String country,

        @NotNull(message = "Age cannot be null")
        @Min(value = 18, message = "Age must be at least 18")
        @Max(value = 120, message = "Age must be less than or equal to 120")
        Integer age,

        @NotNull(message = "Sex cannot be null")
        @Pattern(regexp = "^(male|female|other)$", message = "Sex must be either male, female, or other")
        String sex
) {
}
