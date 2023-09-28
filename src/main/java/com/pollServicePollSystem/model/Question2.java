package com.pollServicePollSystem.model;

import java.util.List;

public class Question2 {
    private Long questionId;
    private String questionTitle;
    private List<AnswerOption> answerOption;

    public Question2(){}
    public Question2(Long questionId, String questionTitle, List<AnswerOption> answerOption) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.answerOption = answerOption;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public List<AnswerOption> getAnswerOption() {
        return answerOption;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setAnswerOption(List<AnswerOption> answerOption) {
        this.answerOption = answerOption;
    }
}
