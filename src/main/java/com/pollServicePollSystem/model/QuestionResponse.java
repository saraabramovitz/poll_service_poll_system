package com.pollServicePollSystem.model;

public class QuestionResponse {
    private Long questionId;
    private String questionTitle;
    private Option option;

    public QuestionResponse() {
    }

    public QuestionResponse(Long questionId, String questionTitle, Option option) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.option = option;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Option getOption() {
        return option;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}