package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.Question;

import java.util.List;

public interface QuestionRepository {
    void createQuestion(Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById(Long questionId);
    List<Question> getAllPollQuestions();
    Question getQuestionByQuestionTitle(Question question);
    String getAnswerByQuestionId(String answer, Long questionId);
    Option getOptionByQuestionIdAndOptionID(Long questionId, Option option);
    boolean isOptionExists(String optionTitle);
}
