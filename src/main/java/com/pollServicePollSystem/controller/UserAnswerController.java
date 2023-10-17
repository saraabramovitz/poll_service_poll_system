package com.pollServicePollSystem.controller;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poolAnswer")

public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;


    @PostMapping("/saveUserPoll")
    public void saveUserPollAnswer(@RequestBody UserPoll userPoll) {
        userAnswerService.saveUserPollAnswer(userPoll);
    }

    @DeleteMapping("/deleteUserAnswers/{userId}")
    public void deletePollAnswersByUserId(@PathVariable Long userId){
        userAnswerService.deletePollAnswersByUserId(userId);
    }

    @GetMapping ("/getUsersAnsweredCountByQuestionId/{questionId}")
    public Long getUsersAnsweredCountByQuestionId(@PathVariable Long questionId){
        return userAnswerService.getUsersAnsweredCountByQuestionId(questionId);
    }

    @GetMapping("/getOptionCount/{questionId}")
    public OptionResponseTry getOptionCountByQuestionId(@PathVariable Long questionId){
        return userAnswerService.getOptionCountByQuestionId(questionId);
    }

    @GetMapping("/getAllQuestionOptionCount")
    public List<OptionResponseTry> getAllQuestionOptionCount(){
        return userAnswerService.getAllQuestionOptionCount();
    }
    @GetMapping("/getUserAnswerAmount/{userId}")
    public Long getUserAnswerAmountByUserId(@PathVariable Long userId){
        return userAnswerService.getUserAnswerAmountByUserId(userId);
    }

    @GetMapping("/getAllUserAnswers/{userId}")
    public List<UserAnswerResponse> getAllPollQuestions(@PathVariable Long userId){
        return userAnswerService.getAllUserAnswers(userId);
    }

}
