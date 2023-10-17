package com.pollServicePollSystem.model;

import java.util.List;

public class OptionResponseTry {

    private Long questionId;
    private String questionTitle;
    private Option option;
    private List<Option> options;

    public OptionResponseTry(){}
    public OptionResponseTry(Long questionId, String questionTitle, Option option) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.option = option;
    }

    public OptionResponseTry(Long questionId, String questionTitle, List<Option> options) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.options = options;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public Option getOption() {
        return option;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
