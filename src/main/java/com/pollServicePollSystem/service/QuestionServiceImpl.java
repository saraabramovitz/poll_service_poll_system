package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    public static final Integer ANSWER_OPTION_AMOUNT = 4;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserAnswerService userAnswerService;


    @Override
    public void createQuestion(Question question) {
        if (isValidQuestionForCreate(question)) {
            questionRepository.createQuestion(question);
        }
    }

    @Override
    public void updateQuestion(Question question) {
        if (isValidQuestionForUpdate(question)){
            questionRepository.updateQuestion(question);
        }
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
    public boolean isValidQuestionForCreate(Question question) {
        ArrayList<Option> optionsArray = question.getAnswerOptions();
        Question existingQuestion = questionRepository.getQuestionByQuestionTitle(question);

        if (existingQuestion == null) {
            if (optionsArray.size() == ANSWER_OPTION_AMOUNT) {
                if (isValidOptionsForCreate(optionsArray)) {
                    return true;
                } else { System.out.println("Invalid options"); }
            } else { System.out.println("Invalid amount of options."); }
        } else { System.out.println("The question already exists."); }
        return false;
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
    public boolean isValidQuestionForUpdate(Question question) {
        ArrayList<Option> optionsArray = question.getAnswerOptions();
        Question existingQuestion = questionRepository.getQuestionByQuestionTitle(question);

        if (existingQuestion == null || existingQuestion.getQuestionId() == question.getQuestionId()) {
            if (optionsArray.size() <= ANSWER_OPTION_AMOUNT) {
                if (isValidOptionsForUpdate(question, optionsArray)) {
                    return true;
                } else { System.out.println("Invalid options"); }
            } else { System.out.println("Invalid amount of options."); }
        } else { System.out.println("The question already exists."); }
        return false;
    }

    @Override
    public boolean isValidOptionsForUpdate(Question question, ArrayList<Option> optionsArray){
        Set<String> uniqueOptionTitle = new HashSet<>();
        Set<Long> uniqueOptionId = new HashSet<>();

        for (Option option : optionsArray) {
            String optionTitle = option.getOptionTitle();
            Long optionId = option.getOptionId();

            if (!uniqueOptionTitle.add(optionTitle) || !uniqueOptionId.add(optionId)) {
                System.out.println("Duplicate option: " + optionTitle);
                return false;
            }
            if (questionRepository.getOptionByQuestionIdAndOptionID(question.getQuestionId(), option) == null) {
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


}


















