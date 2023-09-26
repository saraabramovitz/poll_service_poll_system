package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.Question;

public interface QuestionRepository {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);
}
