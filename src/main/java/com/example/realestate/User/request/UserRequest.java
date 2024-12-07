package com.example.realestate.User.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
         String id,
         String username,
         @NotNull(message = "User firstname required")
         String firstName,
         @NotNull(message = "User lastname required")
         String lastName,
         @Email(message = "User email is not valid email address")
         String email,
         String password,
         String phone
) {

}
