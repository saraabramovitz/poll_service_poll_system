package com.pollServicePollSystem.model;

public class UserAnswer {
    private Long questionId;
    private String answer;

    public UserAnswer() {}

    public UserAnswer(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}