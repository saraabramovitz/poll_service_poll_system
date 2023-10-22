package com.pollServicePollSystem.model;

import java.util.List;

public class UserPollAnswer {
    private Long userId;
    private List<UserAnswer> userAnswers;

    public UserPollAnswer(){}

    public UserPollAnswer(Long userId, List<UserAnswer> userAnswers) {
        this.userId = userId;
        this.userAnswers = userAnswers;
    }

    public Long getUserId() {
        return userId;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserAnswers(List<UserAnswer> userPoll) {
        this.userAnswers = userPoll;
    }
}
