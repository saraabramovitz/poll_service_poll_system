package com.pollServicePollSystem.model;

public class UserAnswerResponse {
    private Long questionId;
    private String questionTitle;
    private Long optionId;
    private String optionTitle;

    public UserAnswerResponse(Long questionId, String questionTitle, Long optionId, String optionTitle) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.optionId = optionId;
        this.optionTitle = optionTitle;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }
}

