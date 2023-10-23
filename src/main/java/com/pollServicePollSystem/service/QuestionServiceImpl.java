package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    public static final Integer ANSWER_OPTION_AMOUNT = 4;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerService answerService;


    @Override
    public void createQuestion(Question question) {
        List<Option> options = question.getOptions();
        if (isQuestionValidForCreate(question)) {
            if (isOptionsValidForCreate(options)) {
                questionRepository.createQuestion(question);
            }
        }
    }

    @Override
    public void updateQuestion(Question question) {
        if(isQuestionValidForUpdate(question)){
            if (isOptionsValidForUpdate(question)) {
                questionRepository.updateQuestion(question);
            }
        }
    }

    @Override
    public void updateQuestionTitle(Question question) {
        if(isQuestionValidForUpdate(question)) {
            questionRepository.updateQuestionTitle(question);
        }
    }


    @Override
    public void updateQuestionOptions(Question question) {
        if(isOptionsValidForUpdate(question)){
            questionRepository.updateQuestionOptions(question);
        }
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        if (isQuestionIdExist(questionId)){
            answerService.deletePollAnswersByQuestionId(questionId);
            questionRepository.deleteQuestionById(questionId);
        } else {
            throw new IllegalArgumentException("The question with id " + questionId + " does not exist.");
        }
    }


    @Override
    public Question getQuestionById(Long questionId) {
        if (isQuestionIdExist(questionId)){
            List<QuestionResponse> questionResponseList = questionRepository.getQuestionById(questionId);
            Question question = setQuestionFromQuestionResponse(questionResponseList);
            return question;
        } else {
            throw new IllegalArgumentException("The question with id " + questionId + " does not exist.");
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        List<QuestionResponse> questionResponseList = questionRepository.getAllQuestions();
        List<Question> questionList = new ArrayList<>();
        List<Option> optionList = new ArrayList<>();

        Long previousId = null;
        Long questionId = null;
        String questionTitle = null;

        for (QuestionResponse response : questionResponseList) {
            Long questionResponseId = response.getQuestionId();
            String questionResponseTitle = response.getQuestionTitle();
            Option option = response.getOption();
            if (previousId != null && !previousId.equals(questionResponseId)) {
                questionList.add(new Question(previousId, questionTitle, new ArrayList<>(optionList)));
                optionList.clear();
            }

            previousId = questionResponseId;
            questionId = questionResponseId;
            questionTitle = questionResponseTitle;
            optionList.add(option);

        }
        if (questionId != null) {
            questionList.add(new Question(questionId, questionTitle, new ArrayList<>(optionList)));
        }
        return questionList;
    }

    @Override
    public Question getQuestionByQuestionTitle(String questionTitle){
        List<QuestionResponse> questionResponseList = questionRepository.getQuestionByQuestionTitle(questionTitle);
        Question question = setQuestionFromQuestionResponse(questionResponseList);
        if(questionResponseList.size() != 0){
            return question;
        } else {
            return null;
        }
    }

    @Override
    public boolean isQuestionValidForCreate(Question question) {
        Long questionId = question.getQuestionId();
        String questionTitle = question.getQuestionTitle();
        if(isQuestionIdExist(questionId)){
            throw new IllegalArgumentException("Question id already exists.");
        }
        if (getQuestionByQuestionTitle(questionTitle) != null) {
            throw new IllegalArgumentException("Question title already exists.");
        }
        return true;
    }


    @Override
    public boolean isQuestionValidForUpdate(Question question) {
        Long questionId = question.getQuestionId();
        String questionTitle = question.getQuestionTitle();
        Question questionResponseByTitle = getQuestionByQuestionTitle(questionTitle);
        if(!isQuestionIdExist(questionId)){
            throw new IllegalArgumentException("Question id does not exists.");
        }
        if (questionResponseByTitle == null || questionResponseByTitle.getQuestionId().equals(questionId)) {
            return true;
        } else {
            throw new IllegalArgumentException("Question title already exists.");
        }
    }

    @Override
    public boolean isOptionsValidForCreate(List<Option> options) {
        if (options.size() == ANSWER_OPTION_AMOUNT){
            Set<String> uniqueOptionTitle = new HashSet<>();
            for (Option option : options) {
                String optionTitle = option.getOptionTitle();
                if (!uniqueOptionTitle.add(optionTitle)) {
                    throw new IllegalArgumentException("Duplicated options.");
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid option amount.");
        }
        return true;
    }

    @Override
    public boolean isOptionsValidForUpdate(Question question){
        Long questionId = question.getQuestionId();
        List<Option> options = question.getOptions();
        Set<String> uniqueOptionTitle = new HashSet<>();
        Set<Long> uniqueOptionId = new HashSet<>();

        if(!isQuestionIdExist(questionId)){
            throw new IllegalArgumentException("Question id does not exists.");
        }
        if (options.size() <= ANSWER_OPTION_AMOUNT) {
            for (Option option : options) {
                String optionTitle = option.getOptionTitle();
                Long optionId = option.getOptionId();

                if (!uniqueOptionTitle.add(optionTitle) || !uniqueOptionId.add(optionId)) {
                    throw new IllegalArgumentException("Duplicate options: " + optionTitle + ".");
                }
                if (questionRepository.getOptionByQuestionIdAndOptionId(questionId, optionId) == null) {
                    throw new IllegalArgumentException("Invalid option id.");
                }
                if (questionRepository.isOptionExists(optionTitle)) {
                    throw new IllegalArgumentException("Option already exist in question.");
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid option amount.");
        }
        return true;
    }

    @Override
    public Question setQuestionFromQuestionResponse(List<QuestionResponse> questionResponseList) {
        ArrayList<Option> options = new ArrayList<>();

        Long questionResponseId = null;
        String questionResponseTitle = null;

        for (QuestionResponse response : questionResponseList){
            questionResponseId = response.getQuestionId();
            questionResponseTitle = response.getQuestionTitle();

            Option option = response.getOption();
            options.add(option);
        }

        return new Question(questionResponseId, questionResponseTitle, options);
    }


    @Override
    public Option getOptionByQuestionIdAndOptionId(Long questionId, Long optionId) {
        return questionRepository.getOptionByQuestionIdAndOptionId(questionId, optionId);
    }

    @Override
    public Long getQuestionIdByOptionId(Long optionId) {
        return questionRepository.getQuestionIdByOptionId(optionId);
    }

    public Boolean isQuestionIdExist(Long questionId){
        if(questionRepository.isQuestionIdExist(questionId) !=0 ){
            return true;
        } else {
            return false;
        }
    }

}


















