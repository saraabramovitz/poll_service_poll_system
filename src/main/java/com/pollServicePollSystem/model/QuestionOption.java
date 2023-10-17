package com.pollServicePollSystem.model;

import java.util.ArrayList;

public class QuestionOption implements QuestionAble{
    private Long questionId;
    private ArrayList<Option> answerOptions;

    public QuestionOption(Long questionId, ArrayList<Option> answerOptions) {
        this.questionId = questionId;
        this.answerOptions = answerOptions;
    }

    public Long getQuestionId() {
        return questionId;
    }


    public ArrayList<Option> getAnswerOptions() {
        return answerOptions;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


    public void setAnswerOptions(ArrayList<Option> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
