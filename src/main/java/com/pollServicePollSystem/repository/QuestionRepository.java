package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.Question;

import java.util.List;

public interface QuestionRepository {
   void createQuestion(Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById(Long questionId);
    List<Question> getAllPollQuestions();
    Question getQuestionByQuestionTitle(String questionTitle);
    String getAnswerByQuestionId(String answer, Long questionId);
}
