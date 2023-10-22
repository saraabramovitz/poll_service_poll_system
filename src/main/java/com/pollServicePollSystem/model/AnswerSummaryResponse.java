package com.pollServicePollSystem.model;

public class AnswerSummaryResponse {
    private Long questionId;
    private String questionTitle;
    private OptionCount optionCount;

    public AnswerSummaryResponse(Long questionId, String questionTitle, OptionCount optionCount) {
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

    public OptionCount getOptionCount() {
        return optionCount;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionCount(OptionCount optionCount) {
        this.optionCount = optionCount;
    }
}

