package com.example.realestate.User;


public record UserRequest(
        String id,
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        String phone
) {

}
