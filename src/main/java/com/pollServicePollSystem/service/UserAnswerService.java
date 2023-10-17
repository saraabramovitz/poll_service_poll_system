package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;

import java.util.List;

public interface UserAnswerService {
    void saveUserPollAnswer(UserPoll userPoll);
    void deletePollAnswersByUserId(Long userId);
    void deletePollAnswersByQuestionId(Long questionId);
    Boolean isQuestionExist (Long questionId);
    boolean isPollEligibleForUser(UserPoll userPoll);
    Long getUsersAnsweredCountByQuestionId(Long questionId);
    OptionResponseTry getOptionCountByQuestionId(Long questionId);
    List<OptionResponseTry> getAllQuestionOptionCount();
    Long getUserAnswerAmountByUserId(Long userId);
    List<UserAnswerResponse> getAllUserAnswers(Long userId);

}
