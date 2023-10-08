package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.mapper.OptionMapper;
import com.pollServicePollSystem.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pollServicePollSystem.model.Option;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{

    public static final String QUESTION_TABLE_NAME = "question";
    public static final String ANSWER_OPTION_TABLE_NAME = "answer_option";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OptionMapper optionMapper;


    @Override
    public void createQuestion (Question question) {
        String sqlQuestion = "INSERT INTO " + QUESTION_TABLE_NAME + " (question_title) values (?)";
        jdbcTemplate.update(sqlQuestion, question.getQuestionTitle());
        Long lostCreatedQuestionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        ArrayList<Option> answerOptionsArray = question.getAnswerOptions();
        String sql = "insert into  "+ ANSWER_OPTION_TABLE_NAME + " (question_id, option_type, answer_option_title) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new OptionBatchPreparedStatementSetter(answerOptionsArray, lostCreatedQuestionId));
    }

    @Override
    public void updateQuestion(Question question) {
        String sql = "UPDATE " + QUESTION_TABLE_NAME + " SET question_title = ? WHERE question_id = ?";
        jdbcTemplate.update(sql, question.getQuestionTitle(), question.getQuestionId());

        ArrayList<Option> answerOptionsArray = question.getAnswerOptions();
        for (Option option : answerOptionsArray) {
            String updateOptionSql = "UPDATE " + ANSWER_OPTION_TABLE_NAME +
                    " SET answer_option_title = ? WHERE question_id = ? AND answer_option_id = ?";
            jdbcTemplate.update(updateOptionSql, option.getOptionTitle(), question.getQuestionId(), option.getOptionId());
        }
    }


    @Override
    public void deleteQuestionById(Long questionId) {
        String deleteQuestionOptionSql = "DELETE FROM " + ANSWER_OPTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(deleteQuestionOptionSql, questionId);

        String deleteQuestionSql = "DELETE FROM " + QUESTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(deleteQuestionSql, questionId);
    }


    @Override
    public Question getQuestionById(Long questionId) {
        String sql = "SELECT * FROM " + QUESTION_TABLE_NAME +
                " JOIN " + ANSWER_OPTION_TABLE_NAME +
                " ON answer_option.question_id = question.question_id " +
                " WHERE question.question_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, questionMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Question> getAllPollQuestions(){
        String sql =  "SELECT * FROM " + QUESTION_TABLE_NAME +
                " JOIN " + ANSWER_OPTION_TABLE_NAME +
                " ON answer_option.question_id = question.question_id ";
        return jdbcTemplate.query(sql, questionMapper);
    }


    @Override
    public Question getQuestionByQuestionTitle(Question question) {
        String questionTitle = question.getQuestionTitle();
        String sql = "SELECT * FROM " + QUESTION_TABLE_NAME +
                " JOIN " + ANSWER_OPTION_TABLE_NAME +
                " ON answer_option.question_id = question.question_id " +
                " WHERE question.question_title = ?";
        try {
            return jdbcTemplate.queryForObject(sql, questionMapper, questionTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public String getAnswerByQuestionId(String answer, Long questionId) {
        String sql = "SELECT " + answer + " FROM " + QUESTION_TABLE_NAME + " WHERE question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{questionId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Option getOptionByQuestionIdAndOptionID(Long questionId, Option option) {
        String sql = "SELECT * FROM " + ANSWER_OPTION_TABLE_NAME +
                " WHERE answer_option_id = ? AND question_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, optionMapper, option.getOptionId(), questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public boolean isOptionExists(String optionTitle) {
        String sql = "SELECT COUNT(*) FROM " + ANSWER_OPTION_TABLE_NAME + " WHERE answer_option_title = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, optionTitle);
        return count > 0;
    }

}
