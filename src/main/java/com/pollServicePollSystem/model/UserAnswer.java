package com.pollServicePollSystem.model;

public class UserAnswer {
    private Long questionId;
    private Long optionId;

    public UserAnswer() {
    }

    public UserAnswer(Long questionId, Long optionId) {
        this.questionId = questionId;
        this.optionId = optionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}