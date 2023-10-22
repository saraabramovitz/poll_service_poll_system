package com.pollServicePollSystem.model;

public class AnswerCount {
    private Long questionId;
    private String questionTitle;
    private Long answerCount;

    public AnswerCount(Long questionId, String questionTitle, Long answerCount) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.answerCount = answerCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setAnswerCount(Long questionCount) {
        this.answerCount = questionCount;
    }
}
