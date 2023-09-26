package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Question;

public interface QuestionService {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);

}
