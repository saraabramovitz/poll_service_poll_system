package com.pollServicePollSystem.model;

public class AnswerOption {
    private Long answerOptionId;
    private String answerOption;

    public AnswerOption() {}

    public Long getAnswerOptionId() {
        return answerOptionId;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOptionId(Long answerOptionId) {
        this.answerOptionId = answerOptionId;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }
}

