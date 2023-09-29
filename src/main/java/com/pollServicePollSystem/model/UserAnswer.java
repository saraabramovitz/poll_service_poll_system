package com.pollServicePollSystem.model;

public class UserAnswer {
    private Long questionId;
    private Long answerId;

    public UserAnswer() {
    }

    public UserAnswer(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}