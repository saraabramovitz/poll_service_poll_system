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
    @Autowired
    private QuestionService questionService;



    @Override
    public void saveUserPollAnswer(UserPoll userPoll) {
        Long userId = userPoll.getUserId();
        List<UserAnswer> userAnswers = userPoll.getUserPoll();

        UserResponse checkIfUserExists = userService.getUserById(userId);

        if(checkIfUserExists == null){
        System.out.println("the user is not exist");
        }
        else {
            if(checkIfUserExists.getIsRegistered() == true){
                for (UserAnswer userAnswer : userAnswers) {
                    if(userAnswerRepository.getUserAnswersByUserId(userId, userAnswer) == null){
                        if(checkIfQuestionExistInPollQuestion(userAnswer.getQuestionId())){
                            userAnswerRepository.saveUserPollAnswer(userId, userAnswer);
                        } else {
                            System.out.println("the Question is not exist in the poll Questions");
                        }
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

    public void deletePollAnswersByQuestionId(Long questionId) {
        userAnswerRepository.deletePollAnswersByQuestionId(questionId);
    }

    public Boolean checkIfQuestionExistInPollQuestion(Long questionId){
        if(questionService.getQuestionById(questionId) == null){
            return false;
        } else {
            return true;
        }
    }

   // public Boolean checkIfAnswerExistInAnswerOption(String Answer){
  //      if(questionService.getAnswerByQuestionId(questionId) == null){
  //          return false;
   //     } else {
   //         return true;
  //      }
   // }

}
