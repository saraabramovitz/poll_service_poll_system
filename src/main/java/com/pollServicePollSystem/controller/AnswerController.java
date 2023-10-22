package com.pollServicePollSystem.controller;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poolAnswer")

public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("/saveUserAnswers")
    public ResponseEntity<?> savePollAnswer(@RequestBody UserPollAnswer userPollAnswer) {
        try {
            answerService.savePollAnswer(userPollAnswer);
            return ResponseEntity.ok("Poll answer saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteUserAnswers/{userId}")
    public ResponseEntity<?> deletePollAnswerByUserId(@PathVariable Long userId){
        try {
            answerService.deletePollAnswerByUserId(userId);
            return ResponseEntity.ok("Answer deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping ("/getAnswerCount/{questionId}")
    public ResponseEntity<?> getAnswerCountByQuestionId(@PathVariable Long questionId){
        try {
            return ResponseEntity.ok(answerService.getAnswerCountByQuestionId(questionId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping ("/getAnswerCount/all")
    public ResponseEntity<?> getAllAnswerCount(){
        try {
            return ResponseEntity.ok(answerService.getAllAnswerCount());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/getAnswerSummary/{questionId}")
    public ResponseEntity<?> getAnswerSummaryByQuestionId(@PathVariable Long questionId){
        try {
            return ResponseEntity.ok(answerService.getAnswerSummaryByQuestionId(questionId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getAnswerSummary/all")
    public ResponseEntity<?> getAllAnswersSummary(){
        try {
            return ResponseEntity.ok(answerService.getAllAnswersSummary());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getUserAnswerCount/{userId}")
    public ResponseEntity<?> getUserAnswerCountById(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(answerService.getUserAnswerCountById(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getUserAnswerCount/all")
    public ResponseEntity<?> getAllUsersAnswerCount(){
        try {
            return ResponseEntity.ok(answerService.getAllUsersAnswerCount());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/getAllUserAnswers/{userId}")
    public ResponseEntity<?> getAllUserAnswers(@PathVariable Long userId){
        try {
            return ResponseEntity.ok(answerService.getAllUserAnswers(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
