package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.model.QuestionResponse;

import java.util.List;

public interface QuestionService {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);
    List<Question> getAllPollQuestions();
   // String getAnswerByQuestionId(String answer, Long questionId);

}
