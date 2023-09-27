package com.pollServicePollSystem.controller;

import com.pollServicePollSystem.userPollSystem.UserResponse;
import com.pollServicePollSystem.userPollSystem.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findUser")
public class UserControllerCheck {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    UserResponse getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

}
