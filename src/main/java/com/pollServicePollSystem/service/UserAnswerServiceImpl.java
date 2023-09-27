package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.UserAnswer;
import com.pollServicePollSystem.model.UserPoll;
import com.pollServicePollSystem.repository.UserAnswerRepository;
import com.pollServicePollSystem.userPollSystem.UserResponse;
import com.pollServicePollSystem.userPollSystem.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserService userService;



    @Override
    public void saveUserPoll(UserPoll userPoll) {
        Long userId = userPoll.getUserId();
        List<UserAnswer> userAnswers = userPoll.getUserPoll();

        UserResponse userResponse = userService.getUserById(userId);

        if(userResponse == null){
        System.out.println("the user is not exist");
        }
        else {
            if(userResponse.getIsRegistered() == true){
                for (UserAnswer userAnswer : userAnswers) {
                    if(userAnswerRepository.getUserAnswersByUserId(userId, userAnswer) == null){
                        userAnswerRepository.saveUserPollAnswer(userId, userAnswer);
                    }else {
                        userAnswerRepository.updateUserPollAnswer(userId, userAnswer);
                    }
                }
                System.out.println("the user can answer the poll");
            } else {
                System.out.println("the user is not registered");
            }
        }
    }


    @Override
    public void deletePollAnswersByUserId(Long userId) {
        userAnswerRepository.deletePollAnswersByUserId(userId);
    }
}
