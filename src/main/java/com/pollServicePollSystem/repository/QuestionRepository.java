package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;

import java.util.ArrayList;
import java.util.List;

public interface QuestionRepository {
    void createQuestion(Question question);
    void updateQuestion (Question question);
    void updateQuestionTitle (QuestionTitle questionTitleImpl);
    void updateQuestionOptions(QuestionAble questionAble);
    void deleteQuestionById (Long questionId);
    Question getQuestionById(Long questionId);
    List<Question> getAllPollQuestions();
    Question getQuestionByQuestionTitle(String questionTitle);
    String getAnswerByQuestionId(String answer, Long questionId);
    Option getOptionByQuestionIdAndOptionId(Long questionId, Long optionId);
    boolean isOptionExists(String optionTitle);
    Long getQuestionIdByOptionId(Long optionId);
    List<QuestionTry> tryNewQuestionMap(Long questionId);
    List<QuestionTry> tryNewQuestionMapAll();
}
