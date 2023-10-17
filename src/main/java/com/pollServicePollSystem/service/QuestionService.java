package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void updateQuestionTitle(QuestionTitle questionTitle);
    void updateQuestionOptions(QuestionOption questionOption);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);
    List<Question> getAllPollQuestions();
    boolean isValidQuestion(Long questionId, String questionTitle);
    boolean isValidQuestionForUpdate(Long questionId, String questionTitle);
    boolean isValidOptionsForCreate(ArrayList<Option> optionsArray);
    boolean isValidOptionsForUpdate(Long questionId, ArrayList<Option> optionsArray);
    Option getOptionByQuestionIdAndOptionId (Long questionId, Long optionId);
    Long getQuestionIdByOptionId(Long optionId);
    Question tryNewQuestionMap(Long questionId);
    List<Question> tryNewQuestionMapAll();

}
