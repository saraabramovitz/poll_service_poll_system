package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.UserAnswer;

public interface UserAnswerRepository {
    void saveUserPollAnswer(Long userId, UserAnswer userAnswers);
    void updateUserPollAnswer(Long userId, UserAnswer userAnswer);
    void deletePollAnswersByUserId(Long userId);
    UserAnswer getUserAnswersByUserId(Long userId, UserAnswer userAnswer);

}
