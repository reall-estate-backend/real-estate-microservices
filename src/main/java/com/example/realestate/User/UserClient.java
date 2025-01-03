package com.example.realestate.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${application.config.users-url}"
)
public interface UserClient {

    @GetMapping("/{user-id}")
    UserResponse findById(@PathVariable("user-id") String userId);

}
