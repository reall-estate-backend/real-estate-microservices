package com.example.realestate.User.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
         String id,
         @NotBlank(message = "Username is required")
         String username,
         @NotBlank(message = "First name is required")
         String firstName,
         @NotBlank(message = "Last name is required")
         String lastName,
         @Email(message = "Invalid email format")
         @NotBlank(message = "Email is required")
         String email,
         String password,
         String phone
) {

}
