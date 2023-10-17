package com.pollServicePollSystem.model;

public class QuestionTitle {
    private Long questionId;
    private String questionTitle;

    public QuestionTitle(Long questionId, String questionTitle) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
}
