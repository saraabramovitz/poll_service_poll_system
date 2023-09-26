package com.pollServicePollSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.QuestionRepository;
import com.pollServicePollSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")

public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/create")
    public void createQuestion(@RequestBody Question question) throws JsonProcessingException {
        questionService.createQuestion(question);
    }

    @PutMapping("/update")
    public void updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
    }

    @DeleteMapping("/delete/{questionId}")
    public void deleteQuestionById(@PathVariable Long questionId){
        questionService.deleteQuestionById(questionId);
    }

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable Long questionId){
        return questionService.getQuestionById(questionId);
    }

}