package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;

import java.util.List;

public interface QuestionRepository {
    void createQuestion(Question question);
    void updateQuestion (Question question);
    void updateQuestionTitle (Question question);
    void updateQuestionOptions(Question question);
    void deleteQuestionById (Long questionId);
    List<QuestionResponse> getQuestionById(Long questionId);
    List<QuestionResponse> getAllQuestions();

    Long isQuestionIdExist(Long questionId);
    List<QuestionResponse> getQuestionByQuestionTitle(String questionTitle);
    String getAnswerByQuestionId(String answer, Long questionId);
    Option getOptionByQuestionIdAndOptionId(Long questionId, Long optionId);
    boolean isOptionExists(String optionTitle);
    Long getQuestionIdByOptionId(Long optionId);

}
