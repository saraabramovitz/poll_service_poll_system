package com.pollServicePollSystem.controller;

import com.pollServicePollSystem.model.UserAnswer;
import com.pollServicePollSystem.model.UserPoll;
import com.pollServicePollSystem.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poolAnswer")

public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;

    @PostMapping("/saveUserPoll")
    public void saveUserPoll(@RequestBody UserPoll userPoll) {
        userAnswerService.saveUserPoll(userPoll);
    }

    @DeleteMapping("/deleteUserAnswers/{userId}")
    public void deletePollAnswersByUserId(@PathVariable Long userId){
        userAnswerService.deletePollAnswersByUserId(userId);
    }




}
