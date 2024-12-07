package com.example.realestate.User.service;


import com.example.realestate.User.entity.User;
import com.example.realestate.User.exception.UserNotFoundException;
import com.example.realestate.User.mapper.UserMapper;
import com.example.realestate.User.repository.UserRepository;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
