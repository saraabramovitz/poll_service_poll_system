package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{

    public static final String POLL_QUESTION_TABLE_NAME = "pool_question";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void createQuestion(Question question) {
        String sql = "INSERT INTO " + POLL_QUESTION_TABLE_NAME + " (question_title, first_answer_option, second_answer_option, third_answer_option, fourth_answer_option) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                question.getQuestionTitle(),
                question.getFirstAnswerOption(),
                question.getSecondAnswerOption(),
                question.getThirdAnswerOption(),
                question.getFourthAnswerOption()
        );

    }

    @Override
    public void updateQuestion(Question question) {
        String sql = "UPDATE " + POLL_QUESTION_TABLE_NAME + " SET question_title=?, first_answer_option=?, second_answer_option=?, third_answer_option=?, fourth_answer_option=? WHERE question_id=?";
        jdbcTemplate.update(
                sql,
                question.getQuestionTitle(),
                question.getFirstAnswerOption(),
                question.getSecondAnswerOption(),
                question.getThirdAnswerOption(),
                question.getFourthAnswerOption(),
                question.getQuestionId()
        );

    }

    @Override
    public void deleteQuestionById(Long questionId) {
        String sql = "DELETE FROM " + POLL_QUESTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sql, questionId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        String sql = "SELECT * FROM " + POLL_QUESTION_TABLE_NAME + " WHERE question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, questionMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }
}
