package com.pollServicePollSystem.model;

public class UserOptionResponse {
    Long questionId;
    String questionTitle;
    String optionATitle;
    Long optionACount;
    String optionBTitle;
    Long optionBCount;
    String optionCTitle;
    Long optionCCount;
    String optionDTitle;
    Long optionDCount;
    public UserOptionResponse (){};
    public UserOptionResponse(Long questionId, String questionTitle, String optionATitle, Long optionACount, String optionBTitle, Long optionBCount, String optionCTitle, Long optionCCount, String optionDTitle, Long optionDCount) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.optionATitle = optionATitle;
        this.optionACount = optionACount;
        this.optionBTitle = optionBTitle;
        this.optionBCount = optionBCount;
        this.optionCTitle = optionCTitle;
        this.optionCCount = optionCCount;
        this.optionDTitle = optionDTitle;
        this.optionDCount = optionDCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getOptionATitle() {
        return optionATitle;
    }

    public Long getOptionACount() {
        return optionACount;
    }

    public String getOptionBTitle() {
        return optionBTitle;
    }

    public Long getOptionBCount() {
        return optionBCount;
    }

    public String getOptionCTitle() {
        return optionCTitle;
    }

    public Long getOptionCCount() {
        return optionCCount;
    }

    public String getOptionDTitle() {
        return optionDTitle;
    }

    public Long getOptionDCount() {
        return optionDCount;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionATitle(String optionATitle) {
        this.optionATitle = optionATitle;
    }

    public void setOptionACount(Long optionACount) {
        this.optionACount = optionACount;
    }

    public void setOptionBTitle(String optionBTitle) {
        this.optionBTitle = optionBTitle;
    }

    public void setOptionBCount(Long optionBCount) {
        this.optionBCount = optionBCount;
    }

    public void setOptionCTitle(String optionCTitle) {
        this.optionCTitle = optionCTitle;
    }

    public void setOptionCCount(Long optionCCount) {
        this.optionCCount = optionCCount;
    }

    public void setOptionDTitle(String optionDTitle) {
        this.optionDTitle = optionDTitle;
    }

    public void setOptionDCount(Long optionDCount) {
        this.optionDCount = optionDCount;
    }
}
