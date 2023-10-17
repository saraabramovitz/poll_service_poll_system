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
    UserAnswerService userAnswerService;


    @Override
    public void createQuestion(Question question) {
        ArrayList<Option> optionsArray = question.getAnswerOptions();

        if (isValidQuestion(question.getQuestionId(), question.getQuestionTitle())) {
            if (optionsArray.size() == ANSWER_OPTION_AMOUNT) {
                if (isValidOptionsForCreate(optionsArray)) {
                    questionRepository.createQuestion(question);
                }
            } else { System.out.println("Invalid amount of options."); }
        }
    }

    @Override
    public void updateQuestion(Question question) {
        ArrayList<Option> optionsArray = question.getAnswerOptions();
        if (isValidQuestion(question.getQuestionId(), question.getQuestionTitle())) {
            if (optionsArray.size() <= ANSWER_OPTION_AMOUNT) {
                if (isValidOptionsForUpdate(question.getQuestionId(), optionsArray)) {
                    questionRepository.updateQuestion(question);
                }
            } else { System.out.println("Invalid amount of options.");
            }
        }
    }

    @Override
    public void updateQuestionTitle(QuestionTitle questionTitle) {
        if (isValidQuestion(questionTitle.getQuestionId(), questionTitle.getQuestionTitle())) {
            questionRepository.updateQuestionTitle(questionTitle);
        }
    }


    @Override
    public void updateQuestionOptions(QuestionOption questionOption) {
        ArrayList<Option> optionsArray = questionOption.getAnswerOptions();
        Long questionId = questionOption.getQuestionId();

        if (optionsArray.size() <= ANSWER_OPTION_AMOUNT) {
            if (isValidOptionsForUpdate(questionId, optionsArray)) {
                questionRepository.updateQuestionOptions(questionOption);
            }
        } else { System.out.println("Invalid amount of options."); }
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        if(questionRepository.getQuestionById(questionId) != null){
            userAnswerService.deletePollAnswersByQuestionId(questionId);
            questionRepository.deleteQuestionById(questionId);
        } else {
            System.out.println("The question with id " + questionId + " does not exist.");
        }
    }

    @Override
    public Question getQuestionById(Long questionId) {
        if(questionRepository.getQuestionById(questionId) != null) {
            return questionRepository.getQuestionById(questionId);
        } else {
            System.out.println("The question with id " + questionId + " does not exist.");
            return null;
        }
    }

    @Override
    public List<Question> getAllPollQuestions() {
        return questionRepository.getAllPollQuestions();
    }

    @Override
    public boolean isValidQuestion(Long questionId, String questionTitle) {
        Question existingQuestion = questionRepository.getQuestionByQuestionTitle(questionTitle);
        if (existingQuestion == null) {
            return true;
        } else {
            System.out.println("The question already exists.");
            return false;
        }
    }

    @Override
    public boolean isValidQuestionForUpdate(Long questionId, String questionTitle) {
        Question existingQuestion = questionRepository.getQuestionByQuestionTitle(questionTitle);
        if (existingQuestion == null || existingQuestion.getQuestionId() == questionId) {
            return true;
        } else {
            System.out.println("The question already exists.");
            return false;
        }
    }

    @Override
    public boolean isValidOptionsForCreate(ArrayList<Option> optionsArray) {
        Set<String> uniqueOptionTitle = new HashSet<>();
        for (Option option : optionsArray) {
            String optionTitle = option.getOptionTitle();
            if (!uniqueOptionTitle.add(optionTitle)) {
                System.out.println("Duplicate option: " + optionTitle);
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean isValidOptionsForUpdate(Long questionId, ArrayList<Option> optionsArray){
        Set<String> uniqueOptionTitle = new HashSet<>();
        Set<Long> uniqueOptionId = new HashSet<>();

        for (Option option : optionsArray) {
            String optionTitle = option.getOptionTitle();
            Long optionId = option.getOptionId();

            if (!uniqueOptionTitle.add(optionTitle) || !uniqueOptionId.add(optionId)) {
                System.out.println("Duplicate option: " + optionTitle);
                return false;
            }
            if (questionRepository.getOptionByQuestionIdAndOptionId(questionId, optionId) == null) {
                System.out.println("Invalid id for this question");
                return false;
            }
            if (questionRepository.isOptionExists(optionTitle)) {
                System.out.println("Option already exist in DB for this question " + optionTitle);
                return false;
            }
        }
        return true;
    }


    @Override
    public Option getOptionByQuestionIdAndOptionId(Long questionId, Long optionId) {
        return questionRepository.getOptionByQuestionIdAndOptionId(questionId, optionId);
    }

    @Override
    public Long getQuestionIdByOptionId(Long optionId) {
        return questionRepository.getQuestionIdByOptionId(optionId);
    }

    @Override
    public Question tryNewQuestionMap(Long questionId) {

        List<QuestionTry> questionTries = questionRepository.tryNewQuestionMap(questionId);
        ArrayList<Option> options = new ArrayList<>();

        Long questionTryId = null;
        String questionTryTitle = null;

        for (QuestionTry questionTry : questionTries){
            questionTryId = questionTry.getQuestionId();
            questionTryTitle = questionTry.getQuestionTitle();

            Option option = questionTry.getOption();
            options.add(option);
        }

        return new Question(questionTryId, questionTryTitle, options);
    }

    @Override
    public List<Question> tryNewQuestionMapAll() {
        List<QuestionTry> questionTries = questionRepository.tryNewQuestionMapAll();
        List<Question> questions = new ArrayList<>();
        ArrayList<Option> options = new ArrayList<>();

        Long previousQuestionTryId = null;
        Long questionTryId = null;
        String questionTryTitle = null;

        for (QuestionTry questionTry : questionTries) {
            if (questionTryId == null) {
                questionTryId = questionTry.getQuestionId();
                questionTryTitle = questionTry.getQuestionTitle();
                previousQuestionTryId = questionTry.getQuestionId();
            }
            if (questionTry.getQuestionId().equals(previousQuestionTryId)) {
                Option option = questionTry.getOption();
                options.add(option);
            } else {
                if (questionTryId != null) {
                    Question question = new Question(questionTryId, questionTryTitle, new ArrayList<>(options));
                    questions.add(question);
                }
                questionTryId = questionTry.getQuestionId();
                questionTryTitle = questionTry.getQuestionTitle();
                options.clear();
                Option option = questionTry.getOption();
                options.add(option);
            }
            previousQuestionTryId = questionTry.getQuestionId();
        }
        if (questionTryId != null) {
            Question question = new Question(questionTryId, questionTryTitle, new ArrayList<>(options));
            questions.add(question);
        }
        return questions;
    }
}


















