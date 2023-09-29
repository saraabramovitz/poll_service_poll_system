package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserAnswerService userAnswerService;

    @Override
    public void createQuestion(Question question) {
        questionRepository.createQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        String questionTitle = question.getQuestionTitle();
        Long questionId = question.getQuestionId();
        Question existQuestionWithSameTitleResponse = questionRepository.getQuestionByQuestionTitle(questionTitle);
        if(existQuestionWithSameTitleResponse == null){
            questionRepository.updateQuestion(question);
        } else if(questionId == existQuestionWithSameTitleResponse.getQuestionId()){
            questionRepository.updateQuestion(question);
        } else {
            System.out.println("can't update questionResponse - the questionResponse already exist.");
        }
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        userAnswerService.deletePollAnswersByQuestionId(questionId);
        questionRepository.deleteQuestionById(questionId);
    }

    public Question getQuestionById(Long questionId){
        return questionRepository.getQuestionById(questionId);
    }

    public List<Question> getAllPollQuestions(){
        return questionRepository.getAllPollQuestions();
    }




}
