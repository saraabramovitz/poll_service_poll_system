package com.pollServicePollSystem.userPollSystem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "UserService",
        url = "${externalApi.userService.url}"
)
public interface UserService {

    @GetMapping("/user/{userId}")
    UserResponse getUserById(@PathVariable Long userId);

}
