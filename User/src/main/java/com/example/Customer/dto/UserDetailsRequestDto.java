package com.example.Customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UserDetailsRequestDto(
        @NotNull(message = "Username cannot be null")
        @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
        @Schema(description = "The username of the user.")
        String username,

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        @Schema(description = "The full name of the user.")
        String name,

        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        @Schema(description = "The email address of the user.")
        String email,

        @NotNull(message = "Password cannot be null")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Schema(description = "The password for the user account.")
        String password,

        @NotNull(message = "Address cannot be null")
        @Size(min = 10, max = 200, message = "Address must be between 10 and 200 characters")
        @Schema(description = "The address of the user.")
        String address,

        @NotNull(message = "Country cannot be null")
        @Pattern(regexp = "^[A-Za-z ]+$", message = "Country should only contain letters and spaces")
        @Schema(description = "The country where the user resides.")
        String country,

        @NotNull(message = "Age cannot be null")
        @Min(value = 18, message = "Age must be at least 18")
        @Max(value = 120, message = "Age must be less than or equal to 120")
        @Schema(description = "The age of the user.")
        Integer age,

        @NotNull(message = "Sex cannot be null")
        @Pattern(regexp = "^(male|female|other)$", message = "Sex must be either male, female, or other")
        @Schema(description = "The gender of the user.")
        String sex
) {
}
