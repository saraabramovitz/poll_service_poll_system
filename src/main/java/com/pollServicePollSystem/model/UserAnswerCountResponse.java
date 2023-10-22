package com.pollServicePollSystem.model;

import com.pollServicePollSystem.userPollSystem.UserResponse;

public class UserAnswerCountResponse {
    private Long userId;
    private Long userAnswerCount;

    public UserAnswerCountResponse() {}

    public UserAnswerCountResponse(Long userId, Long userAnswerCount) {
        this.userId = userId;
        this.userAnswerCount = userAnswerCount;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getUserAnswerCount() {
        return userAnswerCount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserAnswerCount(Long userAnswerCount) {
        this.userAnswerCount = userAnswerCount;
    }
}
