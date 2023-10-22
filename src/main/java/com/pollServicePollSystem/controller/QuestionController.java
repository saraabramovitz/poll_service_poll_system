package com.pollServicePollSystem.controller;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pollQuestion")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        try {
            questionService.createQuestion(question);
            return ResponseEntity.ok("Question created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        try {
            questionService.updateQuestion(question);
            return ResponseEntity.ok("Question updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/updateQuestionTitle")
    public ResponseEntity<?> updateQuestionTitle(@RequestBody Question question) {
        try {
            questionService.updateQuestionTitle(question);
            return ResponseEntity.ok("Question title updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/updateQuestionOptions")
    public ResponseEntity<?> updateQuestionOptions(@RequestBody Question question) {
        try {
            questionService.updateQuestionOptions(question);
            return ResponseEntity.ok("Question option updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long questionId){
        try {
            questionService.deleteQuestionById(questionId);
            return ResponseEntity.ok("Question deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("getQuestion/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long questionId){
        try {
            questionService.getQuestionById(questionId);
            return ResponseEntity.ok(questionService.getQuestionById(questionId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("getQuestion/all")
    public ResponseEntity<?> getQuestions(){
        try {
            return ResponseEntity.ok(questionService.getAllQuestions());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


}
