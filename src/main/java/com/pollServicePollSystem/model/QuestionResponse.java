package com.pollServicePollSystem.model;

public class QuestionResponse {
    private Long questionId;
    private String questionTitle;
    private QuestionAnswerOption firstAnswerOption;
    private QuestionAnswerOption secondAnswerOption;
    private QuestionAnswerOption thirdAnswerOption;
    private QuestionAnswerOption fourthAnswerOption;

    public QuestionResponse(){}

    public QuestionResponse(Long questionId, String questionTitle, QuestionAnswerOption firstAnswerOption, QuestionAnswerOption secondAnswerOption, QuestionAnswerOption thirdAnswerOption, QuestionAnswerOption fourthAnswerOption) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.firstAnswerOption = firstAnswerOption;
        this.secondAnswerOption = secondAnswerOption;
        this.thirdAnswerOption = thirdAnswerOption;
        this.fourthAnswerOption = fourthAnswerOption;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public QuestionAnswerOption getFirstAnswerOption() {
        return firstAnswerOption;
    }

    public QuestionAnswerOption getSecondAnswerOption() {
        return secondAnswerOption;
    }

    public QuestionAnswerOption getThirdAnswerOption() {
        return thirdAnswerOption;
    }

    public QuestionAnswerOption getFourthAnswerOption() {
        return fourthAnswerOption;
    }

    public void setFirstAnswerOption(QuestionAnswerOption firstAnswerOption) {
        this.firstAnswerOption = firstAnswerOption;
    }

    public void setSecondAnswerOption(QuestionAnswerOption secondAnswerOption) {
        this.secondAnswerOption = secondAnswerOption;
    }

    public void setThirdAnswerOption(QuestionAnswerOption thirdAnswerOption) {
        this.thirdAnswerOption = thirdAnswerOption;
    }

    public void setFourthAnswerOption(QuestionAnswerOption fourthAnswerOption) {
        this.fourthAnswerOption = fourthAnswerOption;
    }
}
