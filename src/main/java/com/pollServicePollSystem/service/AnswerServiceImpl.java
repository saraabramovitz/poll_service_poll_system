package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.AnswerRepository;
import com.pollServicePollSystem.userPollSystem.UserResponse;
import com.pollServicePollSystem.userPollSystem.UserService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;


    @Override
    public void savePollAnswer(UserPollAnswer userPollAnswer) {
        try {
            Long userId = userPollAnswer.getUserId();
            List<UserAnswer> userAnswers = userPollAnswer.getUserAnswers();
            if (isPollAvailableForUser(userPollAnswer)) {
                for (UserAnswer userAnswer : userAnswers) {
                    if (isAnswerValid(userAnswer, userId)) {
                        if (answerRepository.getUserAnswersByUserId(userId, userAnswer) == null) {
                            answerRepository.savePollAnswer(userId, userAnswer);
                        } else {
                            answerRepository.updateUserPollAnswer(userId, userAnswer);}
                    }
                }
            }
        } catch(IllegalArgumentException e){
            throw e;
        }
    }


    @Override
    public void deletePollAnswerByUserId(Long userId) {
        answerRepository.deletePollAnswerByUserId(userId);
    }

    @Override
    public void deletePollAnswersByQuestionId(Long questionId) {
        try {
            if(questionService.getQuestionById(questionId) != null){
                answerRepository.deletePollAnswersByQuestionId(questionId);
            } else {
                throw new IllegalArgumentException("Question does not exist.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }




    @Override
    public boolean isPollAvailableForUser(UserPollAnswer userPollAnswer) {
        try {
            Long userId = userPollAnswer.getUserId();
            UserResponse user = userService.getUserById(userId);
            if(user.getIsRegistered()){
                return true;
            } else {
                throw new IllegalArgumentException ("User is not registered.");
            }
        } catch (FeignException e) {
            int responseCode = e.status();
            if (responseCode == 500) {
                throw new IllegalArgumentException ("User does not exist.");
            }
        }
        return false;
    }




    @Override
    public AnswerCount getAnswerCountByQuestionId(Long questionId) {
        try {
            if(questionService.getQuestionById(questionId) != null){
                return answerRepository.getAnswerCountByQuestionId(questionId);
            } else {
                throw new IllegalArgumentException("Question does not exist.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public List<AnswerCount> getAllAnswerCount() {
        return answerRepository.getAllAnswerCount();
    }

    @Override
    public AnswerSummary getAnswerSummaryByQuestionId(Long questionId) {
        try {
            List<AnswerSummaryResponse> answerSummaryResponseList = answerRepository.getAnswerSummaryByQuestionId(questionId);
            List<OptionCount> optionCountList = new ArrayList<>();
            Long answerResponseId = null;
            String answerResponseTitle = null;

            if(answerSummaryResponseList.size() != 0){
                for (AnswerSummaryResponse response : answerSummaryResponseList){
                    answerResponseId = response.getQuestionId();
                    answerResponseTitle = response.getQuestionTitle();
                    OptionCount optionCount = response.getOptionCount();
                    optionCountList.add(optionCount);
                }

                return new AnswerSummary(answerResponseId, answerResponseTitle, optionCountList);
            } else {
                throw new IllegalArgumentException("Question does not exist.");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public List<AnswerSummary> getAllAnswersSummary() {
        List<AnswerSummaryResponse> answerSummaryResponseList = answerRepository.getAllAnswersSummary();
        List<AnswerSummary> answerSummaryList = new ArrayList<>();
        Long previousId = null;
        String previousTitle = null;
        List<OptionCount> optionCountList = new ArrayList<>();

        for (AnswerSummaryResponse response : answerSummaryResponseList) {
            Long answerResponseId = response.getQuestionId();
            String answerResponseTitle = response.getQuestionTitle();
            OptionCount optionCount = response.getOptionCount();

            if (previousId != null && !previousId.equals(answerResponseId)) {
                answerSummaryList.add(new AnswerSummary(previousId, previousTitle, new ArrayList<>(optionCountList)));
                optionCountList.clear();
            }

            previousId = answerResponseId;
            answerResponseId = null;
            previousTitle = answerResponseTitle;
            optionCountList.add(optionCount);
        }

        if (previousId != null) {
            answerSummaryList.add(new AnswerSummary(previousId, previousTitle, new ArrayList<>(optionCountList)));
        }

        return answerSummaryList;
    }

    @Override
    public UserAnswerCount getUserAnswerCountById(Long userId) {
        try {
            if(userService.getUserById(userId) != null){
                UserAnswerCountResponse response = answerRepository.getUserAnswerCountById(userId);
                if(response != null){
                    Long currentUserId = response.getUserId();
                    Long currentUserAnswerCount = response.getUserAnswerCount();
                    UserResponse currentUser = userService.getUserById(currentUserId);

                    UserAnswerCount userAnswerCount = new UserAnswerCount(currentUser, currentUserAnswerCount);
                    return userAnswerCount;
                } else {
                    throw new IllegalArgumentException("No answers found for user.");
                }

            } else {
                throw new IllegalArgumentException("User does not exist.");
            }
        }  catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public List<UserAnswerCount> getAllUsersAnswerCount() {
        List<UserAnswerCountResponse> responseList = answerRepository.getAllUsersAnswerCount();
        List<UserAnswerCount> userAnswerCountsList = new ArrayList<>();

        for (UserAnswerCountResponse response : responseList){
            Long currentUserId = response.getUserId();
            Long currentUserAnswerCount = response.getUserAnswerCount();
            UserResponse currentUser = userService.getUserById(currentUserId);

            UserAnswerCount userAnswerCount = new UserAnswerCount(currentUser, currentUserAnswerCount);
            userAnswerCountsList.add(userAnswerCount);
        }
        return userAnswerCountsList;
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        return answerRepository.getAllUserAnswers(userId);
    }

    @Override
    public Boolean isAnswerValid(UserAnswer userAnswer, Long userId){
        try {
            Long questionId = userAnswer.getQuestionId();
            Long optionId = userAnswer.getOptionId();

            if (!questionService.isQuestionIdExist(questionId)) {
                throw new IllegalArgumentException("Question id " + questionId + " does not exist.");
            }
            if(questionService.getOptionByQuestionIdAndOptionId(questionId, optionId) == null){
                throw new IllegalArgumentException("Option id " + optionId + " is not valid for question id " + questionId + ".");
            }
        }  catch (IllegalArgumentException e) {
            throw e;
        }
        return true;
    }


}
