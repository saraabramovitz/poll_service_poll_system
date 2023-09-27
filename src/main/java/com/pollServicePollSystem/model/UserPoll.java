package com.pollServicePollSystem.model;

import java.util.List;

public class UserPoll {
    private Long userId;
    private List<UserAnswer> userPoll;

    public UserPoll(){}

    public UserPoll(Long userId, List<UserAnswer> userPoll) {
        this.userId = userId;
        this.userPoll = userPoll;
    }

    public Long getUserId() {
        return userId;
    }

    public List<UserAnswer> getUserPoll() {
        return userPoll;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserPoll(List<UserAnswer> userPoll) {
        this.userPoll = userPoll;
    }
}
