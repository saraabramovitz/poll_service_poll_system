package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;


import java.util.List;

public interface QuestionService {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void updateQuestionTitle(Question question);
    void updateQuestionOptions(Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById(Long questionId);
    List<Question> getAllQuestions();
    Question getQuestionByQuestionTitle(String questionTitle);
    boolean isQuestionValidForCreate(Question question);
    boolean isQuestionValidForUpdate(Question question);
    boolean isOptionsValidForCreate(List<Option> optionsArray);
    boolean isOptionsValidForUpdate(Question question);
    Question setQuestionFromQuestionResponse(List<QuestionResponse> questionResponseList);
    Option getOptionByQuestionIdAndOptionId (Long questionId, Long optionId);
    Long getQuestionIdByOptionId(Long optionId);
    Boolean isQuestionIdExist(Long questionId);


}
