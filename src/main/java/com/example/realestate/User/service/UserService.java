package com.example.realestate.User.service;


import com.example.realestate.User.dtos.CredentialsDto;
import com.example.realestate.User.dtos.SignUpDto;
import com.example.realestate.User.dtos.UserDto;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.exception.UserNotFoundException;
import com.example.realestate.User.exceptions.AppException;
import com.example.realestate.User.mapper.UserMapper;
import com.example.realestate.User.repository.UserRepository;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }


    public UserDto findByLogin(String login) {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public String createUser(UserRequest userRequest) {
        var user = userRepository.save(userMapper.toUser(userRequest));
        return user.getId();
    }
    public void updateUser(@Valid UserRequest userRequest) {

        var user = userRepository.findById(userRequest.id())
                .orElseThrow(()-> new UserNotFoundException(
                        format("Cannot update user :: NO user found with the provided id :: %s", userRequest.id())
                ));

        mergeUser(user, userRequest);
        userRepository.save(user);
    }

    private void mergeUser(User user, @Valid UserRequest userRequest) {

        if (StringUtils.isNotBlank(userRequest.firstName())) {
            user.setFirstName(userRequest.firstName());
        }
        if (StringUtils.isNotBlank(userRequest.lastName())) {
            user.setLastName(userRequest.lastName());
        }
        if (StringUtils.isNotBlank(userRequest.email())) {
            user.setEmail(userRequest.email());
        }
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper :: fromUser)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    public UserResponse findById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::fromUser)
                .orElseThrow(()-> new UserNotFoundException(format("Cannot find user :: %s", userId)));
    }


    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
