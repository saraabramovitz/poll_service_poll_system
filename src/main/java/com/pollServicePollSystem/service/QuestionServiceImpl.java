package com.pollServicePollSystem.service;

import com.pollServicePollSystem.model.AnswerOption;
import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.model.Question2;
import com.pollServicePollSystem.model.QuestionResponse;
import com.pollServicePollSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserAnswerService userAnswerService;



    @Override
    public void createQuestion(Question question) {
        String questionTitle = question.getQuestionTitle();
        if(questionRepository.getQuestionQuestionTitle(questionTitle) == null){
            questionRepository.createQuestion(question);
        } else {
            System.out.println("can't create questionResponse - the questionResponse already exist.");
        }
    }

    @Override
    public void updateQuestion(Question question) {
        String questionTitle = question.getQuestionTitle();
        Long questionId = question.getQuestionId();
        Question existQuestionWithSameTitleResponse = questionRepository.getQuestionQuestionTitle(questionTitle);
        if(existQuestionWithSameTitleResponse == null){
            questionRepository.updateQuestion(question);
        } else if(questionId == existQuestionWithSameTitleResponse.getQuestionId()){
            questionRepository.updateQuestion(question);
        } else {
            System.out.println("can't update questionResponse - the questionResponse already exist.");
        }
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        userAnswerService.deletePollAnswersByQuestionId(questionId);
        questionRepository.deleteQuestionById(questionId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.getQuestionById(questionId);
    }

    public List<Question> getAllPollQuestions(){
        return questionRepository.getAllPollQuestions();
    }

    @Override
    public void createQuestion2(Question2 question2) {
        List<AnswerOption> answerOptions = question2.getAnswerOption();
        if (answerOptions.size() == 4){
            questionRepository.createQuestion2InQuestionTable(question2);

           // questionRepository.createQuestion2InOptionTable(question2);

        }

    }

    // public String getAnswerByQuestionId(String answer, Long questionId){
//
   // }
}
