package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;

import java.util.List;

public interface AnswerService {
    void savePollAnswer(UserPollAnswer userPollAnswer);
    void deletePollAnswerByUserId(Long userId);
    void deletePollAnswersByQuestionId(Long questionId);
    boolean isPollAvailableForUser(UserPollAnswer userPollAnswer);
    AnswerCount getAnswerCountByQuestionId(Long questionId);
    List<AnswerCount> getAllAnswerCount();
    AnswerSummary getAnswerSummaryByQuestionId(Long questionId);
    List<AnswerSummary> getAllAnswersSummary();
    UserAnswerCount getUserAnswerCountById(Long userId);
    List<UserAnswerCount> getAllUsersAnswerCount();
    List<UserAnswerResponse> getAllUserAnswers(Long userId);
    Boolean isAnswerValid(UserAnswer userAnswer, Long userId);

}
