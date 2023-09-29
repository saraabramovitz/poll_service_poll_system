package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{

    public static final String POLL_QUESTION_TABLE_NAME = "pool_question";
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String ANSWER_OPTION_TABLE_NAME = "answer_option";

    public static final String OPTION_TYPE_A = "a";
    public static final String OPTION_TYPE_B = "b";
    public static final String OPTION_TYPE_C = "c";
    public static final String OPTION_TYPE_D = "d";


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QuestionMapper questionMapper;



    public void createQuestion(Question question) {
        String sql = "INSERT INTO " + QUESTION_TABLE_NAME + " (question_title) values (?)";
        jdbcTemplate.update(sql, question.getQuestionTitle());
        Long lostCreatedQuestionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        String sqlA = "INSERT INTO " + ANSWER_OPTION_TABLE_NAME + " (question_id, option_type, answer_option_title) values (?, ?, ?)";
        jdbcTemplate.update(
                sqlA,
                lostCreatedQuestionId,
                OPTION_TYPE_A,
                question.getOptionA()
        );
        String sqlB = "INSERT INTO " + ANSWER_OPTION_TABLE_NAME + " (question_id, option_type, answer_option_title) values (?, ?, ?)";
        jdbcTemplate.update(
                sqlB,
                lostCreatedQuestionId,
                OPTION_TYPE_B,
                question.getOptionB());

        String sqlC = "INSERT INTO " + ANSWER_OPTION_TABLE_NAME + " (question_id, option_type, answer_option_title) values (?, ?, ?)";
        jdbcTemplate.update(
                sqlC,
                lostCreatedQuestionId,
                OPTION_TYPE_C,
                question.getOptionC());

        String sqlD = "INSERT INTO " + ANSWER_OPTION_TABLE_NAME + " (question_id, option_type, answer_option_title) values (?, ?, ?)";
        jdbcTemplate.update(
                sqlD,
                lostCreatedQuestionId,
                OPTION_TYPE_D,
                question.getOptionD());
    }



    @Override
    public void updateQuestion(Question question) {
        Long questionId = question.getQuestionId();
        Long optionId = question.getOptionAId();
        String sql = "UPDATE " + QUESTION_TABLE_NAME + " SET question_title=?, WHERE id=?";
        jdbcTemplate.update(
                sql,
                questionId);

        String sql1 = "UPDATE " + ANSWER_OPTION_TABLE_NAME + " SET question_title=?, WHERE id=?";
        jdbcTemplate.update(
                sql,
                questionId);

    }

    @Override
    public void deleteQuestionById(Long questionId) {
        String sql = "DELETE FROM " + POLL_QUESTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sql, questionId);
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
    public List<Question> getAllPollQuestions(){
        String sql =  "SELECT * FROM " + QUESTION_TABLE_NAME +
                " JOIN " + ANSWER_OPTION_TABLE_NAME +
                " ON answer_option.question_id = question.question_id ";
        return jdbcTemplate.query(sql, questionMapper);
    }

    @Override
    public Question getQuestionByQuestionTitle(String questionTitle) {
        return null;
    }


    public String getAnswerByQuestionId(String answer, Long questionId) {
        String sql = "SELECT " + answer + " FROM " + POLL_QUESTION_TABLE_NAME + " WHERE question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{questionId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



}
