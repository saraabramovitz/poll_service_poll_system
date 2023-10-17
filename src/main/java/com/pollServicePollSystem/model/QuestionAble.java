package com.pollServicePollSystem.model;

import java.util.ArrayList;

public interface QuestionAble {
    Long getQuestionId();
    ArrayList<Option> getAnswerOptions();
    void setQuestionId(Long questionId);
    void setAnswerOptions(ArrayList<Option> answerOptions);
}
