package com.pollServicePollSystem.model;

import java.util.ArrayList;

public class Question {
    private Long questionId;
    private String questionTitle;
    private ArrayList<Option> answerOptions;

    public Question(Long questionId, String questionTitle, ArrayList<Option> answerOptions) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.answerOptions = answerOptions;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public ArrayList<Option> getAnswerOptions() {
        return answerOptions;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setAnswerOptions(ArrayList<Option> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
