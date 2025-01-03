package com.example.realestate.User.mapper;

import com.example.realestate.User.dtos.SignUpDto;
import com.example.realestate.User.dtos.UserDto;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserMapper {

    public User toUser(UserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .id(request.id())
                .username(request.username())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())
                .build();
    }

    public UserResponse fromUser(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public UserDto toUserDto(User user) {

        String tocken = "";
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                tocken

        );
    }

    public User signUpToUser(SignUpDto request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(Arrays.toString(request.getPassword()))
                .build();
    }
}


