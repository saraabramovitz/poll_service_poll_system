package com.pollServicePollSystem.model;

import java.util.List;

public class AnswerSummary {

    private Long questionId;
    private String questionTitle;
    private List<OptionCount> optionCount;

    public AnswerSummary(){}

    public AnswerSummary(Long questionId, String questionTitle, List<OptionCount> optionCount) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.optionCount = optionCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public List<OptionCount> getOptionCount() {
        return optionCount;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionCount(List<OptionCount> options) {
        this.optionCount = options;
    }
}
