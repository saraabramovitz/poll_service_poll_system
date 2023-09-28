package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.UserPoll;
import com.pollServicePollSystem.model.UserAnswer;

public interface UserAnswerService {
    void saveUserPollAnswer(UserPoll userPoll);
    void deletePollAnswersByUserId(Long userId);
    void deletePollAnswersByQuestionId(Long questionId);
}
