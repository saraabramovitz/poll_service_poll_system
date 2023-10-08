package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {
    void createQuestion (Question question);
    void updateQuestion (Question question);
    void deleteQuestionById (Long questionId);
    Question getQuestionById (Long questionId);
    List<Question> getAllPollQuestions();
    boolean isValidQuestionForCreate(Question question);
    boolean isValidOptionsForCreate(ArrayList<Option> optionsArray);
    boolean isValidQuestionForUpdate(Question question);
    boolean isValidOptionsForUpdate(Question question, ArrayList<Option> optionsArray);

}
