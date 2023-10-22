package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    public static final String ANSWER_TABLE_NAME = "answer";
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String OPTION_TABLE_NAME = "option";


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    AnswerSummaryResponseMapper answerSummaryResponseMapper;
    @Autowired
    UserAnswerResponseMapper userAnswerResponseMapper;
    @Autowired
    AnswerCountMapper answerCountMapper;
    @Autowired
    UserAnswerCountMapper userAnswerCountMapper;



    @Override
    public void savePollAnswer(Long userId, UserAnswer userAnswer) {
        String sql = "INSERT INTO " + ANSWER_TABLE_NAME + " (user_id, question_id, option_id) values (?, ?, ?)";
        jdbcTemplate.update(
            sql,
            userId,
            userAnswer.getQuestionId(),
            userAnswer.getOptionId());
    }

    @Override
    public void updateUserPollAnswer(Long userId, UserAnswer userAnswer) {
        String sql = "UPDATE " + ANSWER_TABLE_NAME + " SET option_id =? WHERE user_id=? AND question_id=?" ;
        jdbcTemplate.update(
            sql,
            userAnswer.getOptionId(),
            userId,
            userAnswer.getQuestionId());
    }

    @Override
    public void deletePollAnswerByUserId(Long userId) {
        String sql = "DELETE FROM " + ANSWER_TABLE_NAME + " WHERE user_id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void deletePollAnswersByQuestionId(Long questionId) {
        String sql = "DELETE FROM " + ANSWER_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sql, questionId);
    }

    @Override
    public UserAnswer getUserAnswersByUserId(Long userId, UserAnswer userAnswer){
        Long questionId = userAnswer.getQuestionId();
        String sql = "SELECT * FROM " + ANSWER_TABLE_NAME + " WHERE user_id=? AND question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, answerMapper, userId, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public AnswerCount getAnswerCountByQuestionId(Long questionId) {
        String sql =
            "SELECT " +
                "question.question_id ," +
                "question.question_title ," +
                "COUNT(answer.user_id) AS answer_count " +
                "FROM " + QUESTION_TABLE_NAME + " " +
                "LEFT JOIN " + ANSWER_TABLE_NAME + " " +
                "ON question.question_id = answer.question_id " +
                "WHERE question.question_id = ? " +
                "GROUP BY question.question_id, question.question_title;";
        try {
            return jdbcTemplate.queryForObject(sql, answerCountMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AnswerCount> getAllAnswerCount() {
        String sql =
            "SELECT " +
                "question.question_id ," +
                "question.question_title ," +
            "COUNT(answer.user_id) AS answer_count " +
            "FROM " + QUESTION_TABLE_NAME + " " +
            "LEFT JOIN " + ANSWER_TABLE_NAME + " " +
            "ON question.question_id = answer.question_id " +
            "GROUP BY question.question_id, question.question_title;";
        try {
            return jdbcTemplate.query(sql, answerCountMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AnswerSummaryResponse> getAnswerSummaryByQuestionId(Long questionId) {
        String sql =
            "SELECT " +
                "question.question_id, " +
                "question.question_title, " +
                "option.option_id, " +
                "option.option_type, " +
                "option.option_title, " +
                "COALESCE(COUNT(answer.user_id), 0) AS answer_count " +
            "FROM " + QUESTION_TABLE_NAME + " " +
            "JOIN (SELECT * FROM option WHERE question_id = ?) option " +
            "ON question.question_id = option.question_id " +
            "LEFT JOIN " + ANSWER_TABLE_NAME + " " +
            "ON question.question_id = answer.question_id " +
            "AND option.option_id = answer.option_id " +
            "GROUP BY " + "question.question_id, question.question_title, option.option_id, option.option_type, option.option_title " +
            "ORDER BY question.question_id, option.option_id;";
        try {
            return jdbcTemplate.query(sql, answerSummaryResponseMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AnswerSummaryResponse> getAllAnswersSummary() {
        String sql =
            "SELECT " +
                "question.question_id, " +
                "question.question_title, " +
                "option.option_id, " +
                "option.option_type, " +
                "option.option_title, " +
                "COUNT(answer.user_id) AS answer_count " +
            "FROM " + QUESTION_TABLE_NAME + " " +
            "JOIN (SELECT * FROM option) option " +
            "ON question.question_id = option.question_id " +
            "LEFT JOIN " + ANSWER_TABLE_NAME  + " " +
            "ON question.question_id = answer.question_id " +
            "AND option.option_id = answer.option_id " +
            "GROUP BY question.question_id, question.question_title, option.option_id, option.option_type, option.option_title " +
            "ORDER BY question.question_id, option.option_id;";
        try {
            return jdbcTemplate.query(sql, answerSummaryResponseMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserAnswerCountResponse getUserAnswerCountById(Long userId) {
        String sql =
            "SELECT" +
                " answer.user_id," +
            " COUNT(DISTINCT answer.question_id) AS answers_count" +
            " FROM " + ANSWER_TABLE_NAME +
            " WHERE USER_ID = ?" +
            " GROUP BY answer.user_id;";
        try {
            return jdbcTemplate.queryForObject(sql, userAnswerCountMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserAnswerCountResponse> getAllUsersAnswerCount() {
        String sql =
            "SELECT" +
                " answer.user_id," +
                " COUNT(DISTINCT answer.question_id) AS answers_count" +
                " FROM " + ANSWER_TABLE_NAME +
                " GROUP BY answer.user_id;";
        try {
            return jdbcTemplate.query(sql, userAnswerCountMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        String sql =
            "SELECT " +
                "question.question_id, " +
                "question.question_title, " +
                "option.option_id, " +
                "option.option_type, " +
                "option.option_title " +
            "FROM " +  ANSWER_TABLE_NAME + " " +
            "JOIN " +  OPTION_TABLE_NAME + " ON answer.option_id = option.option_id " +
            "JOIN " + QUESTION_TABLE_NAME + " ON answer.question_id = question.question_id " +
            "WHERE answer.user_id = ?";
        try {
            return jdbcTemplate.query(sql, userAnswerResponseMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
