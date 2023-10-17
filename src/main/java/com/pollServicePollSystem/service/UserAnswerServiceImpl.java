package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.UserAnswerRepository;
import com.pollServicePollSystem.userPollSystem.UserResponse;
import com.pollServicePollSystem.userPollSystem.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;


    @Override
    public void saveUserPollAnswer(UserPoll userPoll) {
        Long userId = userPoll.getUserId();
        List<UserAnswer> userAnswers = userPoll.getUserPoll();

        if (isPollEligibleForUser(userPoll)) {
            for (UserAnswer userAnswer : userAnswers) {
                Long questionId = userAnswer.getQuestionId();
                Long optionId = userAnswer.getAnswerId();
                if (questionService.getOptionByQuestionIdAndOptionId(questionId, optionId) != null) {
                    if (userAnswerRepository.getUserAnswersByUserId(userId, userAnswer) == null) {
                        userAnswerRepository.saveUserPollAnswer(userId, userAnswer);
                    } else { userAnswerRepository.updateUserPollAnswer(userId, userAnswer); }
                } else {
                    System.out.println("The answer is not valid."); }
            }
        }
    }


    @Override
    public void deletePollAnswersByUserId(Long userId) {
        userAnswerRepository.deletePollAnswersByUserId(userId);
    }

    @Override
    public void deletePollAnswersByQuestionId(Long questionId) {
        userAnswerRepository.deletePollAnswersByQuestionId(questionId);
    }

    @Override
    public Boolean isQuestionExist (Long questionId){
        if(questionService.getQuestionById(questionId) == null){
            System.out.println("The question is not exist.");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean isPollEligibleForUser(UserPoll userPoll){
        Long userId = userPoll.getUserId();
        if (userService.getUserById(userId) != null) {
            if (userService.getUserById(userId).getIsRegistered()) {
                return true;
            } else { System.out.println("The user is not registered.");}
        } else { System.out.println("The user is not exist.");}
        return false;
    }

    @Override
    public Long getUsersAnsweredCountByQuestionId(Long questionId) {
        return userAnswerRepository.getUsersAnsweredCountByQuestionId(questionId);
    }

    @Override
    public OptionResponseTry getOptionCountByQuestionId(Long questionId) {
        List<OptionResponseTry> optionResponseTry = userAnswerRepository.getOptionCountByQuestionId(questionId);

        List<Option> options = new ArrayList<>();

        Long optionResponseId = null;
        String optionResponseTitle = null;

        for (OptionResponseTry optionResponseTry1 : optionResponseTry){
            optionResponseId = optionResponseTry1.getQuestionId();
            optionResponseTitle = optionResponseTry1.getQuestionTitle();

            Option option = optionResponseTry1.getOption();
            options.add(option);
        }

        return new OptionResponseTry(optionResponseId, optionResponseTitle, options);
    }

    @Override
    public List<OptionResponseTry> getAllQuestionOptionCount() {
        List<OptionResponseTry> optionResponseTry = userAnswerRepository.getAllQuestionOptionCount();
        List<OptionResponseTry> optionResponseArray = new ArrayList<>();
        ArrayList<Option> options = new ArrayList<>();

        Long previousOptionResponseId = null;
        Long optionResponseId = null;
        String optionResponseTitle = null;

        for (OptionResponseTry optionResponseTry1 : optionResponseTry) {
            if (optionResponseId == null) {
                optionResponseId = optionResponseTry1.getQuestionId();
                optionResponseTitle = optionResponseTry1.getQuestionTitle();
                previousOptionResponseId = optionResponseTry1.getQuestionId();
            }
            if (optionResponseTry1.getQuestionId().equals(previousOptionResponseId)) {
                Option option = optionResponseTry1.getOption();
                options.add(option);
            } else {
                if (optionResponseId != null) {
                    OptionResponseTry optionResponse = new OptionResponseTry(optionResponseId, optionResponseTitle, new ArrayList<>(options));
                    optionResponseArray.add(optionResponse);
                }
                optionResponseId = optionResponseTry1.getQuestionId();
                optionResponseTitle = optionResponseTry1.getQuestionTitle();
                options.clear();
                Option option = optionResponseTry1.getOption();
                options.add(option);
            }
            previousOptionResponseId = optionResponseTry1.getQuestionId();
        }
        if (optionResponseId != null) {
            OptionResponseTry optionResponse = new OptionResponseTry(optionResponseId, optionResponseTitle, new ArrayList<>(options));
            optionResponseArray.add(optionResponse);
        }
        return optionResponseArray;
    }



    @Override
    public Long getUserAnswerAmountByUserId(Long userId) {
        return userAnswerRepository.getUserAnswerAmountByUserId(userId);
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        return userAnswerRepository.getAllUserAnswers(userId);
    }


}
