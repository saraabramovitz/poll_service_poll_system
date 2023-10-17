package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.OptionResponseTry;
import com.pollServicePollSystem.model.UserAnswer;
import com.pollServicePollSystem.model.UserAnswerResponse;
import com.pollServicePollSystem.model.UserOptionResponse;

import java.util.List;

public interface UserAnswerRepository {
    void saveUserPollAnswer(Long userId, UserAnswer userAnswers);
    void updateUserPollAnswer(Long userId, UserAnswer userAnswer);
    void deletePollAnswersByUserId(Long userId);
    void deletePollAnswersByQuestionId(Long questionId);
    UserAnswer getUserAnswersByUserId(Long userId, UserAnswer userAnswer);
    Long getUsersAnsweredCountByQuestionId(Long questionId);
    List<OptionResponseTry> getOptionCountByQuestionId(Long questionId);
    List<OptionResponseTry> getAllQuestionOptionCount();
    Long getUserAnswerAmountByUserId(Long userId);
    List<UserAnswerResponse> getAllUserAnswers(Long userId);


}
