package com.pollServicePollSystem.model;

import java.util.List;

public class Question{
    private Long questionId;
    private String questionTitle;
    private List<Option> options;

    public Question(Long questionId, String questionTitle, List<Option> options) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.options = options;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptions(List<Option> answerOptions) {
        this.options = answerOptions;
    }
}
