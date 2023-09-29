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

        try {
            UserResponse checkIfUserExists = userService.getUserById(userId);
            if (checkIfUserExists == null) {
                throw new IllegalArgumentException("The user does not exist.");
            }
            if (!checkIfUserExists.getIsRegistered()) {
                throw new IllegalArgumentException("The user is not registered.");
            }

            for (UserAnswer userAnswer : userAnswers) {
                if (userAnswerRepository.getUserAnswersByUserId(userId, userAnswer) == null) {
                    if (checkIfQuestionExistInPollQuestion(userAnswer.getQuestionId())) {
                        userAnswerRepository.saveUserPollAnswer(userId, userAnswer);
                    } else {
                        throw new IllegalStateException("The question does not exist in the poll questions.");
                    }
                } else {
                    userAnswerRepository.updateUserPollAnswer(userId, userAnswer);
                }
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
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
