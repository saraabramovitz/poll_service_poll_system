package com.pollServicePollSystem.model;

public class Question {
    private Long questionId;
    private String questionTitle;
    private Long optionAId;
    private String optionA;
    private Long optionBId;
    private String optionB;
    private Long optionCId;
    private String optionC;
    private Long optionDId;
    private String optionD;

    public Question(Long questionId, String questionTitle, Long optionAId, String optionA, Long optionBId, String optionB, Long optionCId, String optionC, Long optionDId, String optionD) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.optionAId = optionAId;
        this.optionA = optionA;
        this.optionBId = optionBId;
        this.optionB = optionB;
        this.optionCId = optionCId;
        this.optionC = optionC;
        this.optionDId = optionDId;
        this.optionD = optionD;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Long getOptionAId() {
        return optionAId;
    }

    public String getOptionA() {
        return optionA;
    }

    public Long getOptionBId() {
        return optionBId;
    }

    public String getOptionB() {
        return optionB;
    }

    public Long getOptionCId() {
        return optionCId;
    }

    public String getOptionC() {
        return optionC;
    }

    public Long getOptionDId() {
        return optionDId;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionAId(Long optionAId) {
        this.optionAId = optionAId;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionBId(Long optionBId) {
        this.optionBId = optionBId;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionCId(Long optionCId) {
        this.optionCId = optionCId;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionDId(Long optionDId) {
        this.optionDId = optionDId;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
