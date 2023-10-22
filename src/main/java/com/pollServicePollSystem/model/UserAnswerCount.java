package com.pollServicePollSystem.model;

import com.pollServicePollSystem.userPollSystem.UserResponse;

public class UserAnswerCount {
    private UserResponse user;
    private Long userAnswerCount;

    UserAnswerCount(){}

    public UserAnswerCount(UserResponse user, Long userAnswerCount) {
        this.user = user;
        this.userAnswerCount = userAnswerCount;
    }

    public UserResponse getUser() {
        return user;
    }

    public Long getUserAnswerCount() {
        return userAnswerCount;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public void setUserAnswerCount(Long userAnswerCount) {
        this.userAnswerCount = userAnswerCount;
    }
}
