package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.model.QuestionResponse;

import java.util.List;

public interface QuestionRepository {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);
    List<Question> getAllPollQuestions();
    Question getQuestionQuestionTitle(String questionTitle);
    String getAnswerByQuestionId(String answer, Long questionId);
}
