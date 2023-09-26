package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void createQuestion(Question question) {
        questionRepository.createQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.updateQuestion(question);
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        questionRepository.deleteQuestionById(questionId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.getQuestionById(questionId);
    }
}
