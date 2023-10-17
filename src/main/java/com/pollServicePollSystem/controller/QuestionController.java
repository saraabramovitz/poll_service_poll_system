package com.pollServicePollSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pollQuestion")
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

    @PutMapping("/updateQuestionTitle")
    public void updateQuestionTitle(@RequestBody QuestionTitle questionTitle) {
        questionService.updateQuestionTitle(questionTitle);
    }

    @PutMapping("/updateQuestionOptions")
    public void updateQuestionOptions(@RequestBody QuestionOption questionOption) {
        questionService.updateQuestionOptions(questionOption);
    }


    @DeleteMapping("/delete/{questionId}")
    public void deleteQuestionById(@PathVariable Long questionId){
        questionService.deleteQuestionById(questionId);
    }

    @GetMapping("/{questionId}")
    public Question getNewQuestionById(@PathVariable Long questionId){
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("/all")
    public List<Question> getAllPollQuestions(){
        return questionService.getAllPollQuestions();
    }


    @GetMapping("/tryNewQuestionMap/{questionId}")
    public Question tryNewQuestionMap(@PathVariable Long questionId){
        return questionService.tryNewQuestionMap(questionId);
    }

    @GetMapping("/tryNewQuestionMap/all")
    public List<Question> tryNewQuestionMapAll(){
        return questionService.tryNewQuestionMapAll();
    }







}
