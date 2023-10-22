package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;

import java.util.List;

public interface AnswerRepository {
    void savePollAnswer(Long userId, UserAnswer userAnswers);
    void updateUserPollAnswer(Long userId, UserAnswer userAnswer);
    void deletePollAnswerByUserId(Long userId);
    void deletePollAnswersByQuestionId(Long questionId);
    UserAnswer getUserAnswersByUserId(Long userId, UserAnswer userAnswer);
    AnswerCount getAnswerCountByQuestionId(Long questionId);
    List<AnswerCount> getAllAnswerCount();
    List<AnswerSummaryResponse> getAnswerSummaryByQuestionId(Long questionId);
    List<AnswerSummaryResponse> getAllAnswersSummary();
    UserAnswerCountResponse getUserAnswerCountById(Long userId);
    List<UserAnswerCountResponse> getAllUsersAnswerCount();
    List<UserAnswerResponse> getAllUserAnswers(Long userId);


}
